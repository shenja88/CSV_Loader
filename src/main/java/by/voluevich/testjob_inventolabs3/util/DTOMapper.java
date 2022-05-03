package by.voluevich.testjob_inventolabs3.util;

import by.voluevich.testjob_inventolabs3.dto.PostingDTO;
import by.voluevich.testjob_inventolabs3.dto.ProductDTO;
import by.voluevich.testjob_inventolabs3.dto.ProductItemDTO;
import by.voluevich.testjob_inventolabs3.dto.UserDetailsDTO;
import by.voluevich.testjob_inventolabs3.model.Posting;
import by.voluevich.testjob_inventolabs3.model.Product;
import by.voluevich.testjob_inventolabs3.model.ProductItem;
import by.voluevich.testjob_inventolabs3.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class DTOMapper {

    public static ProductDTO getProdDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .description(product.getDescription())
                .measuringUnits(product.getMeasuringUnits())
                .build();
    }

    public static ProductItemDTO getProdItemDTO(ProductItem productItem) {
        return ProductItemDTO.builder()
                .id(productItem.getId())
                .postingDate(productItem.getPostingDate())
                .product(getProdDTO(productItem.getProduct()))
                .quantity(productItem.getQuantity())
                .amount(productItem.getAmount())
                .currency(productItem.getCurrency())
                .itemPosition(productItem.getItemPosition())
                .build();
    }

    public static PostingDTO getPostingDTO(Posting posting, List<ProductItem> items) {
        List<ProductItemDTO> itemDTOS = items
                .stream()
                .map(DTOMapper::getProdItemDTO)
                .collect(Collectors.toList());
        return PostingDTO.builder()
                .id(posting.getId())
                .uuid(posting.getUuid())
                .contractDate(posting.getContractDate())
                .userName(posting.getUserName())
                .isAuthorize(posting.isAuthorize())
                .productItems(itemDTOS)
                .build();
    }

    public static UserDetailsDTO getUserDetailsDTO(User user) {
        return UserDetailsDTO.builder()
                .id(user.getId())
                .accountName(user.getAccountName())
                .application(user.getApplication())
                .jobTitle(user.getJob().getDescription())
                .departmentTitle(user.getDepartment().getDescription())
                .isActive(user.isActive())
                .build();
    }
}
