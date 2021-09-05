package com.orzechowski.aidme.entities;

public class Category
{
    private long categoryId;
    private final String categoryName;
    private final boolean hasSubcategories;
    private final String miniatureName;
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
}
