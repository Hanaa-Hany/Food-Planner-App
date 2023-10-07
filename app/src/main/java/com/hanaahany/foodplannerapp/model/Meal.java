package com.hanaahany.foodplannerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity(tableName = "Meal_Table",primaryKeys = {"id", "day"})
public class Meal implements Parcelable {
    public Meal() {

    }

    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("idMeal")
    private String id;
    @ColumnInfo
    @SerializedName("strMeal")
    private String nameOfMeal;
    @ColumnInfo
    @SerializedName("strCategory")
    private String category;
    @ColumnInfo
    @SerializedName("strArea")
    private String area;
    @ColumnInfo
    @SerializedName("strInstructions")
    private String instructions;
    @ColumnInfo
    @SerializedName("strMealThumb")
    private String image;
    @ColumnInfo
    @SerializedName("strYoutube")
    private  String youtube;
    @ColumnInfo
    @SerializedName("strIngredient1")
    private  String ingredient1;
    @ColumnInfo
    @SerializedName("strIngredient2")
    private  String ingredient2;
    @ColumnInfo
    @SerializedName("strIngredient3")
    private  String ingredient3;
    @ColumnInfo
    @SerializedName("strIngredient4")
    private  String ingredient4;
    @ColumnInfo
    @SerializedName("strIngredient5")
    private   String ingredient5;
    @ColumnInfo
    @SerializedName("strIngredient6")
    private  String ingredient6;
    @ColumnInfo
    @SerializedName("strIngredient7")
    private   String ingredient7;
    @ColumnInfo
    @SerializedName("strIngredient8")
    private String ingredient8;
    @ColumnInfo
    @SerializedName("strIngredient9")
    private String ingredient9;
    @ColumnInfo
    @SerializedName("strIngredient10")
    private  String ingredient10;
    @ColumnInfo
    @SerializedName("strIngredient11")
    private  String ingredient11;
    @ColumnInfo
    @SerializedName("strIngredient12")
    private String ingredient12;
    @ColumnInfo
    @SerializedName("strIngredient13")
    private   String ingredient13;
    @ColumnInfo
    @SerializedName("strIngredient14")
    private String ingredient14;
    @ColumnInfo
    @SerializedName("strIngredient15")
    private  String ingredient15;
    @ColumnInfo
    @SerializedName("strIngredient16")
    private String ingredient16;
    @ColumnInfo
    @SerializedName("strIngredient17")
    private String ingredient17;
    @ColumnInfo
    @SerializedName("strIngredient18")
    private  String ingredient18;
    @ColumnInfo
    @SerializedName("strIngredient19")
    private  String ingredient19;
    @ColumnInfo
    @SerializedName("strIngredient20")
    private String ingredient20;
    @ColumnInfo
    @SerializedName("strMeasure1")
    private String measure1;
    @ColumnInfo
    @SerializedName("strMeasure2")
    private String measure2;
    @ColumnInfo
    @SerializedName("strMeasure3")
    private String measure3;
    @ColumnInfo
    @SerializedName("strMeasure4")
    private  String measure4;
    @ColumnInfo
    @SerializedName("strMeasure5")
    private String measure5;
    @ColumnInfo
    @SerializedName("strMeasure6")
    private String measure6;
    @ColumnInfo
    @SerializedName("strMeasure7")
    private String measure7;
    @ColumnInfo
    @SerializedName("strMeasure8")
    private String measure8;
    @ColumnInfo
    @SerializedName("strMeasure9")
    private  String measure9;
    @ColumnInfo
    @SerializedName("strMeasure10")
    private  String measure10;
    @ColumnInfo
    @SerializedName("strMeasure11")
    private  String measure11;
    @ColumnInfo
    @SerializedName("strMeasure12")
    private String measure12;
    @ColumnInfo
    @SerializedName("strMeasure13")
    private String measure13;
    @ColumnInfo
    @SerializedName("strMeasure14")
    private String measure14;
    @ColumnInfo
    @SerializedName("strMeasure15")
    private String measure15;
    @ColumnInfo
    @SerializedName("strMeasure16")
    private String measure16;
    @ColumnInfo
    @SerializedName("strMeasure17")
    private String measure17;
    @ColumnInfo
    @SerializedName("strMeasure18")
    private String measure18;
    @ColumnInfo
    @SerializedName("strMeasure19")
    private String measure19;
    @ColumnInfo
    @SerializedName("strMeasure20")
    private String measure20;
    @NonNull

    @ColumnInfo(name = "day")
    private String day="no";


    public Meal(@NonNull String id, String nameOfMeal, String category, String area, String instructions, String image, String youtube, String ingredient1, String ingredient2, String ingredient3, String ingredient4, String ingredient5, String ingredient6, String ingredient7, String ingredient8, String ingredient9, String ingredient10, String ingredient11, String ingredient12, String ingredient13, String ingredient14, String ingredient15, String ingredient16, String ingredient17, String ingredient18, String ingredient19, String ingredient20, String measure1, String measure2, String measure3, String measure4, String measure5, String measure6, String measure7, String measure8, String measure9, String measure10, String measure11, String measure12, String measure13, String measure14, String measure15, String measure16, String measure17, String measure18, String measure19, String measure20, String day) {
        this.id = id;
        this.nameOfMeal = nameOfMeal;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.image = image;
        this.youtube = youtube;
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
        this.ingredient3 = ingredient3;
        this.ingredient4 = ingredient4;
        this.ingredient5 = ingredient5;
        this.ingredient6 = ingredient6;
        this.ingredient7 = ingredient7;
        this.ingredient8 = ingredient8;
        this.ingredient9 = ingredient9;
        this.ingredient10 = ingredient10;
        this.ingredient11 = ingredient11;
        this.ingredient12 = ingredient12;
        this.ingredient13 = ingredient13;
        this.ingredient14 = ingredient14;
        this.ingredient15 = ingredient15;
        this.ingredient16 = ingredient16;
        this.ingredient17 = ingredient17;
        this.ingredient18 = ingredient18;
        this.ingredient19 = ingredient19;
        this.ingredient20 = ingredient20;
        this.measure1 = measure1;
        this.measure2 = measure2;
        this.measure3 = measure3;
        this.measure4 = measure4;
        this.measure5 = measure5;
        this.measure6 = measure6;
        this.measure7 = measure7;
        this.measure8 = measure8;
        this.measure9 = measure9;
        this.measure10 = measure10;
        this.measure11 = measure11;
        this.measure12 = measure12;
        this.measure13 = measure13;
        this.measure14 = measure14;
        this.measure15 = measure15;
        this.measure16 = measure16;
        this.measure17 = measure17;
        this.measure18 = measure18;
        this.measure19 = measure19;
        this.measure20 = measure20;
        this.day = day;
    }

    protected Meal(Parcel in) {
        id = in.readString();
        nameOfMeal = in.readString();
        category = in.readString();
        area = in.readString();
        instructions = in.readString();
        image = in.readString();
        youtube = in.readString();
        ingredient1 = in.readString();
        ingredient2 = in.readString();
        ingredient3 = in.readString();
        ingredient4 = in.readString();
        ingredient5 = in.readString();
        ingredient6 = in.readString();
        ingredient7 = in.readString();
        ingredient8 = in.readString();
        ingredient9 = in.readString();
        ingredient10 = in.readString();
        ingredient11 = in.readString();
        ingredient12 = in.readString();
        ingredient13 = in.readString();
        ingredient14 = in.readString();
        ingredient15 = in.readString();
        ingredient16 = in.readString();
        ingredient17 = in.readString();
        ingredient18 = in.readString();
        ingredient19 = in.readString();
        ingredient20 = in.readString();
        measure1 = in.readString();
        measure2 = in.readString();
        measure3 = in.readString();
        measure4 = in.readString();
        measure5 = in.readString();
        measure6 = in.readString();
        measure7 = in.readString();
        measure8 = in.readString();
        measure9 = in.readString();
        measure10 = in.readString();
        measure11 = in.readString();
        measure12 = in.readString();
        measure13 = in.readString();
        measure14 = in.readString();
        measure15 = in.readString();
        measure16 = in.readString();
        measure17 = in.readString();
        measure18 = in.readString();
        measure19 = in.readString();
        measure20 = in.readString();
        day = in.readString();
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameOfMeal() {
        return nameOfMeal;
    }

    public void setNameOfMeal(String nameOfMeal) {
        this.nameOfMeal = nameOfMeal;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getIngredient1() {
        return ingredient1;
    }

    public void setIngredient1(String ingredient1) {
        this.ingredient1 = ingredient1;
    }

    public String getIngredient2() {
        return ingredient2;
    }

    public void setIngredient2(String ingredient2) {
        this.ingredient2 = ingredient2;
    }

    public String getIngredient3() {
        return ingredient3;
    }

    public void setIngredient3(String ingredient3) {
        this.ingredient3 = ingredient3;
    }

    public String getIngredient4() {
        return ingredient4;
    }

    public void setIngredient4(String ingredient4) {
        this.ingredient4 = ingredient4;
    }

    public String getIngredient5() {
        return ingredient5;
    }

    public void setIngredient5(String ingredient5) {
        this.ingredient5 = ingredient5;
    }

    public String getIngredient6() {
        return ingredient6;
    }

    public void setIngredient6(String ingredient6) {
        this.ingredient6 = ingredient6;
    }

    public String getIngredient7() {
        return ingredient7;
    }

    public void setIngredient7(String ingredient7) {
        this.ingredient7 = ingredient7;
    }

    public String getIngredient8() {
        return ingredient8;
    }

    public void setIngredient8(String ingredient8) {
        this.ingredient8 = ingredient8;
    }

    public String getIngredient9() {
        return ingredient9;
    }

    public void setIngredient9(String ingredient9) {
        this.ingredient9 = ingredient9;
    }

    public String getIngredient10() {
        return ingredient10;
    }

    public void setIngredient10(String ingredient10) {
        this.ingredient10 = ingredient10;
    }

    public String getIngredient11() {
        return ingredient11;
    }

    public void setIngredient11(String ingredient11) {
        this.ingredient11 = ingredient11;
    }

    public String getIngredient12() {
        return ingredient12;
    }

    public void setIngredient12(String ingredient12) {
        this.ingredient12 = ingredient12;
    }

    public String getIngredient13() {
        return ingredient13;
    }

    public void setIngredient13(String ingredient13) {
        this.ingredient13 = ingredient13;
    }

    public String getIngredient14() {
        return ingredient14;
    }

    public void setIngredient14(String ingredient14) {
        this.ingredient14 = ingredient14;
    }

    public String getIngredient15() {
        return ingredient15;
    }

    public void setIngredient15(String ingredient15) {
        this.ingredient15 = ingredient15;
    }

    public String getIngredient16() {
        return ingredient16;
    }

    public void setIngredient16(String ingredient16) {
        this.ingredient16 = ingredient16;
    }

    public String getIngredient17() {
        return ingredient17;
    }

    public void setIngredient17(String ingredient17) {
        this.ingredient17 = ingredient17;
    }

    public String getIngredient18() {
        return ingredient18;
    }

    public void setIngredient18(String ingredient18) {
        this.ingredient18 = ingredient18;
    }

    public String getIngredient19() {
        return ingredient19;
    }

    public void setIngredient19(String ingredient19) {
        this.ingredient19 = ingredient19;
    }

    public String getIngredient20() {
        return ingredient20;
    }

    public void setIngredient20(String ingredient20) {
        this.ingredient20 = ingredient20;
    }

    public String getMeasure1() {
        return measure1;
    }

    public void setMeasure1(String measure1) {
        this.measure1 = measure1;
    }

    public String getMeasure2() {
        return measure2;
    }

    public void setMeasure2(String measure2) {
        this.measure2 = measure2;
    }

    public String getMeasure3() {
        return measure3;
    }

    public void setMeasure3(String measure3) {
        this.measure3 = measure3;
    }

    public String getMeasure4() {
        return measure4;
    }

    public void setMeasure4(String measure4) {
        this.measure4 = measure4;
    }

    public String getMeasure5() {
        return measure5;
    }

    public void setMeasure5(String measure5) {
        this.measure5 = measure5;
    }

    public String getMeasure6() {
        return measure6;
    }

    public void setMeasure6(String measure6) {
        this.measure6 = measure6;
    }

    public String getMeasure7() {
        return measure7;
    }

    public void setMeasure7(String measure7) {
        this.measure7 = measure7;
    }

    public String getMeasure8() {
        return measure8;
    }

    public void setMeasure8(String measure8) {
        this.measure8 = measure8;
    }

    public String getMeasure9() {
        return measure9;
    }

    public void setMeasure9(String measure9) {
        this.measure9 = measure9;
    }

    public String getMeasure10() {
        return measure10;
    }

    public void setMeasure10(String measure10) {
        this.measure10 = measure10;
    }

    public String getMeasure11() {
        return measure11;
    }

    public void setMeasure11(String measure11) {
        this.measure11 = measure11;
    }

    public String getMeasure12() {
        return measure12;
    }

    public void setMeasure12(String measure12) {
        this.measure12 = measure12;
    }

    public String getMeasure13() {
        return measure13;
    }

    public void setMeasure13(String measure13) {
        this.measure13 = measure13;
    }

    public String getMeasure14() {
        return measure14;
    }

    public void setMeasure14(String measure14) {
        this.measure14 = measure14;
    }

    public String getMeasure15() {
        return measure15;
    }

    public void setMeasure15(String measure15) {
        this.measure15 = measure15;
    }

    public String getMeasure16() {
        return measure16;
    }

    public void setMeasure16(String measure16) {
        this.measure16 = measure16;
    }

    public String getMeasure17() {
        return measure17;
    }

    public void setMeasure17(String measure17) {
        this.measure17 = measure17;
    }

    public String getMeasure18() {
        return measure18;
    }

    public void setMeasure18(String measure18) {
        this.measure18 = measure18;
    }

    public String getMeasure19() {
        return measure19;
    }

    public void setMeasure19(String measure19) {
        this.measure19 = measure19;
    }

    public String getMeasure20() {
        return measure20;
    }

    public void setMeasure20(String measure20) {
        this.measure20 = measure20;
    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nameOfMeal);
        parcel.writeString(id);
        parcel.writeString(day);
        parcel.writeString(category);
        parcel.writeString(area);
        parcel.writeString(image);
        parcel.writeString(instructions);
        parcel.writeString(youtube);
        parcel.writeString(measure1);
        parcel.writeString(measure2);
        parcel.writeString(measure3);
        parcel.writeString(measure4);
        parcel.writeString(measure5);
        parcel.writeString(measure6);
        parcel.writeString(measure7);
        parcel.writeString(measure8);
        parcel.writeString(measure9);
        parcel.writeString(measure10);
        parcel.writeString(measure11);
        parcel.writeString(measure12);
        parcel.writeString(measure13);
        parcel.writeString(measure14);
        parcel.writeString(measure15);
        parcel.writeString(measure16);
        parcel.writeString(measure17);
        parcel.writeString(measure18);
        parcel.writeString(measure19);
        parcel.writeString(measure20);
        parcel.writeString(ingredient1);
        parcel.writeString(ingredient2);
        parcel.writeString(ingredient3);
        parcel.writeString(ingredient4);
        parcel.writeString(ingredient5);
        parcel.writeString(ingredient6);
        parcel.writeString(ingredient7);
        parcel.writeString(ingredient8);
        parcel.writeString(ingredient9);
        parcel.writeString(ingredient10);
        parcel.writeString(ingredient11);
        parcel.writeString(ingredient12);
        parcel.writeString(ingredient13);
        parcel.writeString(ingredient14);
        parcel.writeString(ingredient15);
        parcel.writeString(ingredient16);
        parcel.writeString(ingredient17);
        parcel.writeString(ingredient18);
        parcel.writeString(ingredient19);
        parcel.writeString(ingredient20);



    }
}
