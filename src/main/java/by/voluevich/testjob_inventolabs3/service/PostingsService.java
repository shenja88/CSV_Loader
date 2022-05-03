package by.voluevich.testjob_inventolabs3.service;

import by.voluevich.testjob_inventolabs3.dto.PostingDTO;
import by.voluevich.testjob_inventolabs3.dto.SearchFilter;
import by.voluevich.testjob_inventolabs3.model.Posting;
import by.voluevich.testjob_inventolabs3.model.ProductItem;
import by.voluevich.testjob_inventolabs3.repo.PostingRepo;
import by.voluevich.testjob_inventolabs3.repo.ProductItemRepo;
import by.voluevich.testjob_inventolabs3.util.DTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PostingsService {
    private final PostingRepo postingRepo;
    private final ProductItemRepo productItemRepo;

    public PostingDTO getByUuid(long uuid) {
        Posting posting = postingRepo.getByUuid(uuid);
        List<ProductItem> productItems = productItemRepo.getProductItemByPosting(posting);
        return DTOMapper.getPostingDTO(posting, productItems);
    }

    public List<PostingDTO> getAllByFilter(SearchFilter filter) {
        List<Posting> filteredPostings = postingRepo.getAllByContractDateBetween(
                filter.getStart(),
                filter.getEnd());

        return getListDTOs(
                filteredPostings.stream()
                        .filter(posting -> posting.isAuthorize() == filter.isAuthorize())
                        .collect(Collectors.toList()));
    }

    public List<PostingDTO> getByAccountName(String accountName) {
        List<Posting> postings = postingRepo.getAllByUserName(accountName);
        return getListDTOs(postings);
    }

    private List<PostingDTO> getListDTOs(List<Posting> postings) {
        return postings.stream()
                .map(p -> DTOMapper.getPostingDTO(p, productItemRepo.getProductItemByPosting(p)))
                .collect(Collectors.toList());
    }
}
