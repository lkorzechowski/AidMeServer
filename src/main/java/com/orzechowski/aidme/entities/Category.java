package com.orzechowski.aidme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category
{
    @Id
    private long categoryId;
    @Column(name = "category_name", nullable = false)
    private final String categoryName;
    @Column(name = "has_subcategories", nullable = false)
    private final boolean hasSubcategories;
    @Column(name = "miniature_name")
    private final String miniatureName;
    @Column(name = "category_level", nullable = false)
    private final int categoryLevel;

    public Category(String categoryName, boolean hasSubcategories, String miniatureName, int categoryLevel)
    {
        this.categoryName = categoryName;
        this.hasSubcategories = hasSubcategories;
        this.miniatureName = miniatureName;
        this.categoryLevel = categoryLevel;
    }

    public Category(long categoryId, String categoryName, boolean hasSubcategories, String miniatureName,
                    int categoryLevel)
    {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.hasSubcategories = hasSubcategories;
        this.miniatureName = miniatureName;
        this.categoryLevel = categoryLevel;
    }

    public long getCategoryId()
    {
        return categoryId;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public boolean isHasSubcategories()
    {
        return hasSubcategories;
    }

    public String getMiniatureName()
    {
        return miniatureName;
    }

    public int getCategoryLevel()
    {
        return categoryLevel;
    }
}
