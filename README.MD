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

You can run this example from [here](https://github.com/SKAIBlue/SKAIJSONExample).

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
    
        @JSONField                      // If you want to inject data to field, You have to use this annotation.
        public List<JUser> results;     // results is JSONArray so use List
                                        // * Set access level to public! *
     
}

```

JUser.java
```java

class JUser {
    // It must need default initializer for use this library.
    public JUser() { }

    @JSONField
    public String gender;              // gender is String

    @JSONField
    public JUserName name;             // name is JSONObject so you have to create class for it.

    @JSONField
    public JLocation location;         // location is JSONObject so you have to create class for it.

    @JSONField
    public String email;               // email is String

    @JSONField
    public JLogin login;                // login is JSONObject so you have to create class for it.

    @JSONField
    public String dob;

    @JSONField
    public String registered;

    @JSONField
    public String phone;

    @JSONField
    public String cell;

    @JSONField
    public JPicture picture;

    @JSONField
    public String nat;
}

```


JUserName.java
```java
class JUserName {
    public JUserName() {}

    @JSONField
    public String title;

    @JSONField
    public String first;

    @JSONField
    public String last;

    
}
```
JLocation.java

```java
class JLocation {

    public JLocation() {
    }
    
    @JSONField
    public String street;
    
    @JSONField
    public String city;
    
    @JSONField
    public String state;
    
    @JSONField                  // If You want parsing Number type, You must use Object Type.           
    public Integer postcode;    // (Ex: Integer, Double, Boolean ...)         
    
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
    
    
    // This is Event instance.
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


### Methods

```java


    /**
     * get String on HTTP Page and parsing to Object. run async.
     * @param t Type of Object.
     * @param con HTTP Connection.
     * @param activity Activity for getting UI Thread.
     * @param completed Event interface
     */
    public static void getFromHttpConnectionAsync(final Class t, final HttpURLConnection con, final Activity activity, final OnParseCompleted completed);



    /**
     * get String on HTTP Page and parsing to Object.
     * @param t Type of Object.
     * @param con HTTP Connection.
     * @return Parsed Object
     * @throws IOException
     */
    public static Object getFromHttpConnection(Class t, HttpURLConnection con) throws IOException;



    /**
     * Object to json
     * @param o object
     * @return converted JSONObject
     */
    public static JSONObject toJSONObject(Object o);


    /**
     * List to JSONArray
     * @param list list
     * @return converted JSONList
     */
    public static JSONArray toJSONArray(List list);


    /**
     * JSON Array to List
     * @param t    type of list
     * @param json json string
     * @return List
     */
    public static List toList(Class t, String json);


    /**
     * JSONObject to Object
     *
     * @param t    type of object
     * @param json json string
     * @return Object
     */
    public static Object toObject(Class t, String json);
```

