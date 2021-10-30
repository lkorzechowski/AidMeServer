package com.orzechowski.aidme.entities.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category
{
    @Id
    @Column(name = "category_id", nullable = false)
    private long categoryId;
    @Column(name = "category_name", nullable = false)
    private String categoryName;
    @Column(name = "has_subcategories", nullable = false)
    private boolean hasSubcategories;
    @Column(name = "category_miniature_name")
    private String miniatureName;
    @Column(name = "category_level", nullable = false)
    private int categoryLevel;

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
