# SQLiteStorageOptionExample

## About

This app is for educational purpose of learning the following items:
* Storage Mechanism: Read/Write data from/to SQLite DB in Android
* ListView using ListAdaptor widget provided by Android API
* Uses ButterKnife to Bind Views and stuff


## Reference/Inspiration/Motivation
* [Learning Android by Ken Kousen](https://www.safaribooksonline.com/library/view/learning-android/9781491935514/)
* [Code Examples Project - HelloWorldAS (Branch 6-db) by Ken Kousen from Github](https://github.com/kousen/HelloWorldAS/tree/6-db)
* [Google's API Guides for Data Storage](http://developer.android.com/guide/topics/data/data-storage.html#db) 


## Explanation
* First step is create a `DBHelper` class that extends `android.database.sqlite.SQLiteOpenHelper` like [here](https://github.com/AndroidStuff/SQLiteStorageOptionExample/tree/master/src/com/codonomics/demo/adaptors)
* Then you'll have to override the 2 below methods
``` java
public void onCreate(SQLiteDatabase db) {...}
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {...}
```
* But before that you'll have to create a constructor like below to get rid of compiler errors:
``` java
    public DBHelper(Context context) {
        //SQLiteOpenHelper(Context context, String dbname, CursorFactory factory, int version)
        super(context, DB_NAME, null, VERSION);
    }
```

## Recommendation
* I'd highly recommend subscription to [Safari Books Online](https://www.safaribooksonline.com/). It's worth the price and your time. I've got mine and enjoying it. IMHO, it's way cheaper than purchasing a whole bundle of books/videos over the course of one year. Disclaimer: I purchased the subscription at 50% discount.