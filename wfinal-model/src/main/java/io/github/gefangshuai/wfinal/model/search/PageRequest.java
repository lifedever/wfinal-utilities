package io.github.gefangshuai.wfinal.model.search;

import java.io.Serializable;


/**
 * 分页辅助对象
 *
 * @author gefangshuai
 */
public class PageRequest implements Serializable {

    private static final long serialVersionUID = 8280485938848398236L;

    private final int page;     // 当前页号
    private final int size;     // 每页大小

    public PageRequest(int page, int size) {

        if (0 >= page) {
            throw new IllegalArgumentException("Page index must be more than zero!");
        }

        if (0 >= size) {
            throw new IllegalArgumentException("Page size must not be less than or equal to zero!");
        }

        this.page = page;
        this.size = size;
    }


    public int getPageSize() {

        return size;
    }

    public int getPageNumber() {

        return page;
    }


    public int getOffset() {

        return page * size;
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PageRequest)) {
            return false;
        }

        PageRequest that = (PageRequest) obj;

        boolean pageEqual = this.page == that.page;
        boolean sizeEqual = this.size == that.size;


        return pageEqual && sizeEqual;
    }


    @Override
    public int hashCode() {

        int result = 17;

        result = 31 * result + page;
        result = 31 * result + size;

        return result;
    }
}
