package com.Vsupport.India;

public class ExampleItem {
    private int mImageResource;
    private String mText1;
    private String mfile_location;

     ExampleItem(int ImageResource,String Text1,String file_location){
        mImageResource=ImageResource;
        mText1=Text1;
        mfile_location=file_location;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public void setmImageResource(int mImageResource) {
        this.mImageResource = mImageResource;
    }

    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public String getMfile_location() {
        return mfile_location;
    }

    public void setMfile_location(String mfile_location) {
        this.mfile_location = mfile_location;
    }
}
