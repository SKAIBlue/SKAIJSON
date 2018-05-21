### SKAIJSON

You can parse easy JSON string on Android.

Just create a class for your JSON structure and use the library.


### Install

Add this script on Project's build.gradle.

```groovy
allprojects {
    repositories {
        ...
        maven {
            url  "http://dl.bintray.com/jwmtp2/maven/"
        }
    }
}
```

And add this script on Module's build.gradle you want use.

```groovy
dependencies {
    ...
    implementation'com.skaiblue.skaijson:skaijson:1.0.1'
}

```

### Example

We will use [Random User API](https://randomuser.me/api)

JSON Structure of This api

```json
{
  "results":[
    {
      "gender":"female",
      "name":{
        "title":"ms",
        "first":"blanca",
        "last":"fuentes"
      },
      "location":{
        "street":"1460 avenida de la albufera",
        "city":"palma de mallorca",
        "state":"extremadura",
        "postcode":61340
      },
      "email":"blanca.fuentes@example.com",
      "login":{
        "username":"crazypeacock322",
        "password":"johnny5",
        "salt":"48W4f4v4",
        "md5":"9c7e07a3650b61577221bc6b05576cdf",
        "sha1":"33301b21041f52597bb8842e256740d933c1c1cb",
        "sha256":"abd39607d432fb6371bde29fdde791f917d53c2c5cc94ac561f6ca79b72ccde2"
        },
      "dob":"1959-04-17 19:22:51",
      "registered":"2006-07-19 12:49:54",
      "phone":"993-345-745",
      "cell":"682-833-812",
      "id":{
        "name":"DNI",
        "value":"76667431-J"
      },
      "picture":{
        "large":"https://randomuser.me/api/portraits/women/40.jpg",
        "medium":"https://randomuser.me/api/portraits/med/women/40.jpg",
        "thumbnail":"https://randomuser.me/api/portraits/thumb/women/40.jpg"
      },
      "nat":"ES"
    }
  ],
  "info":{
    "seed":"009c4e8be092560f",
    "results":1,
    "page":1,
    "version":"1.1"
  }
}
``` 

Only the data you want to use
```json
{
  "results":[
    {
      "gender":"female",
      "name":{
        "title":"ms",
        "first":"blanca",
        "last":"fuentes"
      },
      "location":{
        "street":"1460 avenida de la albufera",
        "city":"palma de mallorca",
        "state":"extremadura",
        "postcode":61340
      },
      "email":"blanca.fuentes@example.com",
      "login":{
        "username":"crazypeacock322",
        "password":"johnny5",
        },
      "dob":"1959-04-17 19:22:51",
      "registered":"2006-07-19 12:49:54",
      "phone":"993-345-745",
      "cell":"682-833-812",
      "picture":{
        "large":"https://randomuser.me/api/portraits/women/40.jpg",
        "medium":"https://randomuser.me/api/portraits/med/women/40.jpg",
        "thumbnail":"https://randomuser.me/api/portraits/thumb/women/40.jpg"
      },
      "nat":"ES"
    }
  ]
}
``` 

We will create class for this JSON Structure.

JRandomUser.java
```JAVA

class JRandomUser {
    
    // It must need default initializer for use this library.  
    public JRandomUser() { }
    
    @JSONField                  // If you want to inject data to field, You have to use this annotation.
    List<JUser> results;        // results is JSONArray so use List
     
}

```

JUser.java
```java

class JUser {
    // It must need default initializer for use this library.
    public JUser() { }
    
    @JSONField
    String gender;              // gender is String
    
    @JSONField
    JUserName name;             // name is JSONObject so you have to create class for it.
    
    @JSONField
    JLocation location;         // location is JSONObject so you have to create class for it.
    
    @JSONField
    String email;               // email is String
    
    @JSONField
    JLogin login;                // login is JSONObject so you have to create class for it.
    
    @JSONField
    String dob;
    
    @JSONField
    String registered;
    
    @JSONField
    String phone;
    
    @JSONField
    String cell;
    
    @JSONField
    JPicture picture;
    
    @JSONField
    String net;
}

```


JUserName.java
```java
class JUserName {
    public JUserName() {}
    
    @JSONField
    String title;
    
    @JSONField
    String first;
    
    @JSONField
    String last;
    
}
```


```java
class JLocation {

    public JLocation() {
    }
    
    @JSONField
    String street;
    
    @JSONField
    String city;
    
    @JSONField
    String state;
    
    @JSONField          // If You want parsing Number type, You must use Object Type.           
    Integer postcode;   // (Ex: Integer, Double, Boolean ...)         
    
}
```

and so on... 

And You can Parse JSON String on Activity like this


MainActivity.java
```java

public class MainActivity extends AppCompatActivity{

    
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    
    
    @BindView(R.id.text)
    TextView text;
    
    
    private MainActivity that;
    
    
    private SKAIJSON.OnParseCompleted<JRandomUser> randomUserOnParseCompleted = new SKAIJSON.OnParseCompleted<JRandomUser>() {
        @Override
        public void onParseComplete(JRandomUser jRandomUser) {
            text.setText(jRandomUser.toString());
        }
    };
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ButterKnife.bind(this);
        
        setSupportActionBar(toolbar);
        
        that = this;
        
    }
    
    
    @OnClick(R.id.fab)
    void onClickFAB()
    {
        URL url = null;
        
        try {
            
            // Construct HttpURLConnection
            url = new URL("https://randomuser.me/api");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            
            // Use this method
            SKAIJSON.getFromHttpConnectionAsync(JRandomUser.class, con, that, randomUserOnParseCompleted);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}



```
