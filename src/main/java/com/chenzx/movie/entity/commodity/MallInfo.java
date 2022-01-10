package com.chenzx.movie.entity.commodity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2022/1/8 20:01
 */
@Data
public class MallInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 商品id
     */
    private Long id;
    /**
     * 商品名字
     */
    private String name;
    /**
     * 商品销量
     */
    private Long salesVolume;

    /**
     * 商品id
     */
    public Long getId() {
        return id;
    }

    /**
     * 商品id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 商品名字
     */
    public String getName() {
        return name;
    }

    /**
     * 商品名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 商品销量
     */
    public Long getSalesVolume() {
        return salesVolume;
    }

    /**
     * 商品销量
     */
    public void setSalesVolume(Long salesVolume) {
        this.salesVolume = salesVolume;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MallInfo other = (MallInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getSalesVolume() == null ? other.getSalesVolume() == null : this.getSalesVolume().equals(other.getSalesVolume()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSalesVolume() == null) ? 0 : getSalesVolume().hashCode());
        return result;
    }
}
