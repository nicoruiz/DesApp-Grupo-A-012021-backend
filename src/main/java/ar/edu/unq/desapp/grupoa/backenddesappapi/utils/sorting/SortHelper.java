package ar.edu.unq.desapp.grupoa.backenddesappapi.utils.sorting;

import org.springframework.data.domain.Sort.Order;

import java.util.List;

public interface SortHelper {
    List<Order> getSort(String[] sort);
    boolean isValidSort(String[] sort);
}
