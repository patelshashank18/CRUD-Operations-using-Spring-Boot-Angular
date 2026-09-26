package com.example.child_management.response;

/**
 * Standard pagination response used by ChildCare360 APIs
 *
 * This class contains the actual records along with
 * pagination information.
 *
 * @param <T> type of data contained in the response
 */
public class PaginationResponse<T> {

    /**
     * Records returned for the current page.
     */
    private T content;

    /**
     * Current page number.
     */
    private int page;

    /**
     * Number of records requested per page.
     */
    private int size;

    /**
     * Total number of records available.
     */
    private long totalElements;

    /**
     * Total number of pages available.
     */
    private int totalPages;

    /**
     * Creates a pagination response.
     *
     * @param content       records for the current page
     * @param page          current page number
     * @param size          number of records per page
     * @param totalElements total records
     * @param totalPages    total pages
     */
    public PaginationResponse(
            T content,
            int page,
            int size,
            long totalElements,
            int totalPages) {

        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    /**
     * Returns the records for the current page.
     *
     * @return page content
     */
    public T getContent() {
        return content;
    }

    /**
     * Returns the current page number.
     *
     * @return page number
     */
    public int getPage() {
        return page;
    }

    /**
     * Returns the page size.
     *
     * @return page size
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the total number of records.
     *
     * @return total records
     */
    public long getTotalElements() {
        return totalElements;
    }

    /**
     * Returns the total number of pages.
     *
     * @return total pages
     */
    public int getTotalPages() {
        return totalPages;
    }
}
