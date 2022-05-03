package by.voluevich.testjob_inventolabs3.dataloader;

import au.com.bytecode.opencsv.CSVReader;
import by.voluevich.testjob_inventolabs3.model.Posting;
import by.voluevich.testjob_inventolabs3.model.Product;
import by.voluevich.testjob_inventolabs3.model.ProductItem;
import by.voluevich.testjob_inventolabs3.repo.PostingRepo;
import by.voluevich.testjob_inventolabs3.repo.ProductItemRepo;
import by.voluevich.testjob_inventolabs3.repo.ProductRepo;
import by.voluevich.testjob_inventolabs3.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class PostingsCSVLoader implements CSVLoader {
    @Value("${app.resource.file.postings}")
    private String postingsFileName;
    @Value("${app.resource.file.postings.uuid}")
    private String UUID;
    @Value("${app.resource.file.postings.item_position}")
    private String ITEM_POSITION;
    @Value("${app.resource.file.postings.contract_date}")
    private String CONTRACT_DATE;
    @Value("${app.resource.file.postings.posting_date}")
    private String POSTING_DATE;
    @Value("${app.resource.file.postings.product_desc}")
    private String PROD_DESC;
    @Value("${app.resource.file.postings.quantity}")
    private String QUANTITY;
    @Value("${app.resource.file.postings.measuring_units}")
    private String UNITS;
    @Value("${app.resource.file.postings.amount}")
    private String AMOUNT;
    @Value("${app.resource.file.postings.currency}")
    private String CURRENCY;
    @Value("${app.resource.file.postings.username}")
    private String USER_NAME;
    @Value("${app.resource.file.postings.separator}")
    private char SEPARATOR;

    private final PostingRepo postingRepo;
    private final ProductRepo productRepo;
    private final ProductItemRepo productItemRepo;
    private final UserRepo userRepo;

    @Autowired
    public PostingsCSVLoader(PostingRepo postingRepo, ProductRepo productRepo, ProductItemRepo productItemRepo, UserRepo userRepo) {
        this.postingRepo = postingRepo;
        this.productRepo = productRepo;
        this.productItemRepo = productItemRepo;
        this.userRepo = userRepo;
    }

    private final Map<String, Integer> columnPosition = new HashMap<>();


    @Override
    public void startLoading() throws IOException {
        try (CSVReader reader = getCSVReader(postingsFileName, SEPARATOR)) {
            List<String[]> dataRows = reader.readAll();
            dataRows = proceedTabulation(dataRows);

            initDataPosition(dataRows.get(0));
            dataRows.remove(0);

            storingModels(dataRows);
        }
    }

    private void initDataPosition(String[] titleRow) {
        List<String> row = Arrays.stream(titleRow).collect(Collectors.toList());
        columnPosition.put(UUID, row.indexOf(UUID));
        columnPosition.put(ITEM_POSITION, row.indexOf(ITEM_POSITION));
        columnPosition.put(CONTRACT_DATE, row.indexOf(CONTRACT_DATE));
        columnPosition.put(POSTING_DATE, row.indexOf(POSTING_DATE));
        columnPosition.put(PROD_DESC, row.indexOf(PROD_DESC));
        columnPosition.put(QUANTITY, row.indexOf(QUANTITY));
        columnPosition.put(UNITS, row.indexOf(UNITS));
        columnPosition.put(AMOUNT, row.indexOf(AMOUNT));
        columnPosition.put(CURRENCY, row.indexOf(CURRENCY));
        columnPosition.put(USER_NAME, row.indexOf(USER_NAME));
    }

    private void storingModels(List<String[]> dataRows) {
        dataRows.forEach(row -> {
            Product product = storeProduct(row);
            Posting posting = storePosting(row);
            storeProductItem(row, product, posting);
        });
    }

    private Product storeProduct(String[] row) {
        if (!productRepo.existsByDescription(row[columnPosition.get(PROD_DESC)])) {
            Product product = Product.builder()
                    .description(row[columnPosition.get(PROD_DESC)])
                    .measuringUnits(row[columnPosition.get(UNITS)])
                    .build();
            return productRepo.save(product);
        }
        return productRepo.getByDescription(row[columnPosition.get(PROD_DESC)]);
    }

    private void storeProductItem(String[] row, Product product, Posting posting) {
        if (!productItemRepo.existsByProductAndPostingAndPostingDate(
                product,
                posting,
                getTime(row[columnPosition.get(POSTING_DATE)]))) {
            ProductItem productItem = ProductItem.builder()
                    .postingDate(getTime(row[columnPosition.get(POSTING_DATE)]))
                    .product(product)
                    .quantity(Integer.parseInt(row[columnPosition.get(QUANTITY)]))
                    .amount(getDouble(row[columnPosition.get(AMOUNT)]))
                    .currency(row[columnPosition.get(CURRENCY)])
                    .itemPosition(Integer.parseInt(row[columnPosition.get(ITEM_POSITION)]))
                    .posting(posting).build();
            productItemRepo.save(productItem);
        }
    }

    private Posting storePosting(String[] row) {
        if (!postingRepo.existsByUuid(Long.parseLong(row[columnPosition.get(UUID)]))) {
            Posting posting = Posting.builder()
                    .uuid(Long.parseLong(row[columnPosition.get(UUID)]))
                    .contractDate(getTime(row[columnPosition.get(CONTRACT_DATE)]))
                    .isAuthorize(authorizePostingCheck(row[columnPosition.get(USER_NAME)]))
                    .userName(row[columnPosition.get(USER_NAME)])
                    .build();
            return postingRepo.save(posting);
        }
        return postingRepo.getByUuid(Long.parseLong(row[columnPosition.get(UUID)]));
    }

    private boolean authorizePostingCheck(String userName) {
        return userRepo.existsByAccountName(userName) && userRepo.getByAccountName(userName).isActive();
    }
}
