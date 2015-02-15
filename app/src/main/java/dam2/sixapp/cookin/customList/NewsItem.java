package dam2.sixapp.cookin.customList;

public class NewsItem {


    private static String title, desc, image;


    public NewsItem(){}

    public NewsItem(String title, String desc, String img) {
        this.title = title;
        this.desc = desc;
        this.image = img;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }








}
