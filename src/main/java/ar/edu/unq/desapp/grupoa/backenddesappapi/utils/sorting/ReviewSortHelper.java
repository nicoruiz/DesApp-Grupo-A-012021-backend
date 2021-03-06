package ar.edu.unq.desapp.grupoa.backenddesappapi.utils.sorting;

import ar.edu.unq.desapp.grupoa.backenddesappapi.config.SortConfig;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewSortHelper implements SortHelper {

    @Override
    public List<Order> getSort(String[] sort) {
        List<Order> orders = new ArrayList<>();

        if (sort[0].contains(",")) {
            // will sort more than 2 fields
            // sortOrder="field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                if(this.isValidSort(_sort)) {
                    orders.add(new Order(Sort.Direction.fromString(_sort[1]), _sort[0]));
                }
            }
        } else {
            // sort=[field, direction]
            if(this.isValidSort(sort)) {
                orders.add(new Order(Sort.Direction.fromString(sort[1]), sort[0]));
            }
        }
        // If none given sort property was valid, set default one
        if(orders.isEmpty()) {
            orders.add(new Order(Sort.Direction.fromString(SortConfig.DESC), SortConfig.REVIEW_CREATED_ON));
        }

        return orders;
    }

    @Override
    public boolean isValidSort(String[] sort) {
        boolean isValidProperty = !sort[0].isEmpty() &&
                (sort[0].equals(SortConfig.REVIEW_CREATED_ON) || sort[0].equals(SortConfig.REVIEW_RATING));

        boolean isValidDirection = !sort[1].isEmpty() &&
                (sort[1].equals(SortConfig.ASC) || sort[1].equals(SortConfig.DESC));

        return isValidProperty && isValidDirection;
    }
}
