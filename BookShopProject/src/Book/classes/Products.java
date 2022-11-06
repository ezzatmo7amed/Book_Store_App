package Book.classes;

public class Products {
    
    private int book_Id;
    private String book_Name;
    private String book_Description;
    private String book_img;
    private double Book_Price;

    public Products(int book_Id, String book_Name, String book_Description, String book_img, double Book_Price) {
       
        this.book_Id = book_Id;
        this.book_Name = book_Name;
        this.book_Description = book_Description;
        this.book_img = book_img;
        this.Book_Price = Book_Price;
    }

    

    public void setBook_Id(int book_Id) {
        this.book_Id = book_Id;
    }

    public int getBook_Id() {
        return book_Id;
    }

    public void setBook_Name(String book_Name) {
        this.book_Name = book_Name;
    }

    public String getBook_Name() {
        return book_Name;
    }

    public void setBook_Description(String book_Description) {
        this.book_Description = book_Description;
    }

    public String getBook_Description() {
        return book_Description;
    }

    public void setBook_img(String book_img) {
        this.book_img = book_img;
    }

    public String getBook_img() {
        return book_img;
    }

    public void setBook_Price(double Book_Price) {
        this.Book_Price = Book_Price;
    }

    public double getBook_Price() {
        return Book_Price;
    }
}
