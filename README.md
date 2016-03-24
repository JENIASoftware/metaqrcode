# _what is this?_ #
--------------------------------

This is opensource client API for metaqrode ( https://www.metaqrcode.com ).
You can find here examples, piece of code, prototypes, and many other material

# _metaqrcode getting started_ #
--------------------------------


# 1. hello world! #

Using metaqrcode you can upload your XML document and associate it to a qrcode. I hope this is clear!

As basic example of metaqrcode usage, you can try to upload an helloworld document.This is the xml document we want to upload:
 
    <?xml version="1.0" encoding="UTF-8"?>
    <helloWorld>
      <who>Andrea</who>
    </helloWorld>
	
You can upload it using graphical interface (our website) or using metaqrcode REST API.

#### Using Metaqrcode Website ####

* login to metaqrcode
* go to XSD Catalog
* search in metaqrcode XSD catalog for helloworld.xsd (shuold be https://www.metaqrcode.com/api/c/1) and copy the catalog URL
* go to XML Repository and click on uplaod
* specify the default XSD catalog URL with the url of the catalog of helloworld.xsd
* insert previous XML in the textarea
* press OK
* you will see the qrcode of the uploaded xml
* you can use the generated qrcode (by ie download) 

#### Consume Metaqrcode REST API ####

* login to metaqrcode (using login REST API or openidconnect, see below)
* search in metaqrcode catalog for helloworld.xsd and copy the catalog URL into clipboard
	
	<pre>
    //
    // after login we can upload XML
    //
    var data = new FormData(); // create the form data to send within subsequent post
    var requestRepositoryUpload = new Object(); // create the upload xml request 
    requestRepositoryUpload.defaultCatalog=%%catalog url%%; // set the catalog URL the xml is referring to
    requestRepositoryUpload.correlationId=%%application correlation id%%; // if you need to search this qrcode in the future you can use this (your) correlationId. This is optional
    requestRepositoryUpload.personal=false; // is this XML repository entry private? 
    data.append('request', new Blob([JSON.stringify(requestRepositoryUpload)], {
        type: "application/json"
    })); // set the request as fitst parameter in the form data
    var blob = new Blob(%%your xml here%%], { type: "text/xml"}); // create a Blob with the xml
    data.append('xml',blob); // set the xml as second parameter in the form data (in alternative you can use data.append('xml',%%file field%%[0].files[0]);
    $.ajax({
          type: "POST", // method MUST BE post
          url: "https://www.metaqrcode.com/api/rest/json/repository/upload", // XML upload URL
          data: data, // formdata object
          cache: false, 
          contentType: false, 
          processData: false,
          beforeSend: function (request) {
              // use one of two methods depending on login type used
              if (%%sessionToken%%!=null) {
                  request.setRequestHeader("Authorization", "Token "+%%sessionToken%%);
              }
              if (%%accessToken%%!=null) {
                  request.setRequestHeader("Authorization", "Bearer "+%%accessToken%%);
              }
          },
          error: function(jqXHR, textStatus, errorThrown) {
            // error handling 
          },
          success: function(data, textStatus, jqXHR) {
            if (data.returnCode >= 0) { // verify that the response is ok
                data.repositoryGet; // the link to get your XML
                data.qrcodeGet; // *the link to get your QRCODE*   
            } else {
                // error handling 
            }
          } 
        }); // send ajax POST request
        </pre>
        
# 2. Where to find these examples? #

You can find all examples described in this tutorial here : https://github.com/JENIASoftware/metaqrcode/blob/master/metaqrcode-client/metaqrcode-client-js/src/main/webapp/gettingStarted.html

You can find examples about XSD and XML here : https://github.com/JENIASoftware/metaqrcode/tree/master/metaqrcode-client/metaqrcode-client-catalog/src/main/resources

You can also find specific examples about REST API in samples contained here : https://github.com/JENIASoftware/metaqrcode/tree/master/metaqrcode-client/metaqrcode-client-js/src/main/webapp

# 3. Basic Concepts #

Using metaqrcode you can upload your XML document and associate it to a qrcode. I hope this is clear!

An XML document into metaqrcode is called Repository Entry. In metaqrcode the repository is the container of all repository entries, so the repository is the container of all XML documents. Each XML document inside metaqrcode has to be associated with at least one XSD. 

In metaqrcode an XSD is called Catalog Entry. The Catalog is the container of all XSD.

When you upload an XML you have to specify one or more XSD. The XML have to comply with the given XSD. 
Metaqrcode will check compliance during upload phase.
When you upload an XML you can specify one XSD using API (outside the XML) or you can specify one or more XSD inside the XML document.

That's all relevants concepts of metaqrcode. 

# 4. Authentication/Authorization #

Using metaqrcode you can upload your XML document and associate it to a qrcode. I hope this is clear!

Metaqrcode expose rest json and rest xml API. Some API needs authentication to proceed.

To authenticate to metaqrcode you can use two methods : login API or openidconnect.

In every scenario you (developer) need to register yourself and the app you are developing on metaqrcode openidconnect server.

Go to [https://www.metaqrcode.com/oidc](https://www.metaqrcode.com/oidc) to register yourself and your app. After client (app) registration you will obtain a client_id to use in login API.

#### Get the metaqrcode session token ####

	// 
	// logging in using REST API
	//
	var requestLogin = new Object(); // create the login request object
	requestLogin.email=%%the email address%%; // set the userId (email) to be logged in. The user must be registered into metaqrcode
	requestLogin.password=%%password%%; // set the user password
	requestLogin.clientId=%%client_id%%; // client_id of the app is using metaqrcode
	$.ajax({
		  type: "POST", // MUST BE POST
		  url: "https://www.metaqrcode.com/api/rest/json/login/login", // login URL, will be https
		  data: JSON.stringify(requestLogin), // data to send to server
		  dataType: "json",
		  contentType: "application/json; charset=utf-8",
		  error: function(jqXHR, textStatus, errorThrown) {
			// error handling 
		  },
		  success: function(data, textStatus, jqXHR) {
			if (data.returnCode >= 0) { // verify that the response is ok
				data.sessionToken; // sessionToekn to be passed in subsequent authenticated request using "Authorization : Token"
			} else {
				// error handling 
			}
		  } 
		}); // send ajax POST request

#### Using OpenID Connect ####

**authorization_endpoint**: [https://www.metaqrcode.com/oidc/authorize](https://www.metaqrcode.com/oidc/authorize)

For more info see our configuration endpoint: [https://www.metaqrcode.com/oidc/.well-known/openid-configuration](https://www.metaqrcode.com/oidc/.well-known/openid-configuration)

*Note that uploaded XML are "property of" the user has uploaded them. We discourage using one generic user and password for all users of your app.*

*We also discourage use of login REST API. The preferred method to login users is openidconnect.*
*You can register user inside metaqrcode using REST API, but you should never save userid and password of your users.*

# 5. Working with catalog #

Using metaqrcode you can upload your XML document and associate it to a qrcode. I hope this is clear!

Each XML you want to upload on metaqrcode has to comply with a given XSD (catalog entry).You can use an existing XSD or upload your own. Every XSD are shared between all users. It's important to verify if the XSD you need it's already present in catalog before upload a new one.

It's important to reduce number of similar XSD inside metaqrcode server. This is the reason for which in XSD you can use import, inclusion, extensions and all standard XSD feature.

To use that features, you always have to refer to an existing schema by using it's metaqrcode URL. 

## 5.1. upload a new catalog entry (XSD) ##

Suppose you want to upload this XSD

	<xs:schema version="1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
		xmlns="personData.xsd"
		targetNamespace="personData.xsd"
		elementFormDefault="qualified" attributeFormDefault="unqualified">
	  <xs:element name="personData" type="personData"/>
	  <xs:complexType name="personData">
	    <xs:sequence>
	      <xs:element name="firstName" type="xs:string" minOccurs="1"/>
	      <xs:element name="lastName" type="xs:string" minOccurs="1"/>
	      <xs:element name="sex" type="sex" minOccurs="1"/>
	      <xs:element name="birthday" type="xs:dateTime" minOccurs="1"/>
	      <xs:element name="birthPlace" type="xs:string" minOccurs="1"/>
	      <xs:element name="citizenship" type="xs:string" minOccurs="1"/>
	      <xs:element name="address" type="xs:string" minOccurs="1"/>
	      <xs:element name="eMail" type="xs:string" minOccurs="1"/>
	      <xs:element name="taxCode" type="xs:string" minOccurs="1"/>
	      <xs:element name="phone" type="xs:string" minOccurs="1"/>
	      <xs:any processContents="lax" namespace="##other" minOccurs="0" maxOccurs="unbounded"/>
	    </xs:sequence>
	  </xs:complexType>
	  <xs:simpleType name="sex">
	    <xs:restriction base="xs:string">
	      <xs:enumeration value="MALE"/>
	      <xs:enumeration value="OTHER"/>
	      <xs:enumeration value="FEMALE"/>
	    </xs:restriction>
	  </xs:simpleType>
	</xs:schema>

You can upload it using graphical interface (our website) or using metaqrcode REST API.
You should find it already uploaded in catalog at this url : https://www.metaqrcode.com/api/c/2

#### Using Metaqrcode Website ####

* login to metaqrcode
* go to XSD catalog 
* press upload button
* set a name for your XSD (by ie personData.xsd)
* set a description for your XSD
* insert previous XSD in the textarea
* press upload button

#### Consume Metaqrcode REST API ####

	//
	// after login we can upload XSD
	//
	// we have to send the json request and the XSD, to do that we have to create a formdata
	var data = new FormData();
	var requestCatalogUpload = new Object();
	requestCatalogUpload.name=%%name of the catalog to be uploaded%%;
	requestCatalogUpload.description=%%description of the catalog%%;
	// in the form we put two attribute : request -> the json request
	data.append('request', new Blob([JSON.stringify(requestCatalogUpload)], {
	    type: "application/json"
	}));
	// and xsd -> the catalog entry (XSD) to be uploaded
	var blob = new Blob(%%your xsd here%%], { type: "text/xml"}); // create a Blob with the xsd
	data.append('xsd',blob); // set the xsd as second parameter in the form data (in alternative you can use data.append('xsd',<file field>[0].files[0]);	
	$.ajax({
		  type: "POST", // MUST BE POST
		  url: "http://"+SERVER+":"+PORT+"/api/rest/json/catalog/upload", // XSD upload URL
		  data: data, // data to send to server
		  cache: false,
		  contentType: false,
		  processData: false,
		  beforeSend: function (request) {
			// use one of two methods depending on login type used
			  if (%%sessionToken%%!=null) {
		              request.setRequestHeader("Authorization", "Token "+%%sessionToken%%);
			  }
			  if (%%accessToken%%!=null) {
		              request.setRequestHeader("Authorization", "Bearer "+%%accessToken%%);
			  }
	      },
		  error: function(jqXHR, textStatus, errorThrown) {
			// error handling 
		  },
		  success: function(data, textStatus, jqXHR) {
			if (data.returnCode >= 0) {
				data.catalogGet; // *the link to get your XSD*
			} else {
				// error handling 
			}
		  } 
		});
	return false;

## 5.2. upload repository entry (XML) referring a catalog entry (XSD) ##

Once uploaded previous catalog entry (XSD) you can upload a new repository entry (XML) using that catalog entry (XSD).

You have two way to have your repository entry (XML) referring to a catalog entry (XSD) : 

* specify a default catalog entry in requestRepositoryUpload API
* refer explicitly to given catalog entries (XSDs) from inside the repository entry (XML)

In first case you can ONLY specify ONE catalog entry. Your entire XML must comply with that XSD.

In the second case your XML can refer many catalog entries (XSD). We will now see this case. The first case is shown in "hello world" example.

Suppose you want to upload this repository entry (XML) : 

	<?xml version="1.0" encoding="UTF-8"?>
	<personData 
		xmlns="personData.xsd"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
		xsi:schemaLocation="personData.xsd https://www.metaqrcode.com/api/c/2">
	  <firstName>firstName</firstName>
	  <lastName>lastName</lastName>
	  <sex>MALE</sex>
	  <birthday>2001-12-31T12:00:00</birthday>
	  <birthPlace>birthPlace</birthPlace>
	  <citizenship>citizenship</citizenship>
	  <address>address</address>
	  <eMail>eMail</eMail>
	  <taxCode>taxCode</taxCode>
	  <phone>phone</phone>
	</personData>

In this example you have an XML that refers to previous uploaded XSD : personData.xsd. 
In the simple example the URL of the previously uploaded XSD is : http://www.metaqrcode/api/c/2

When you uploaded the catalog entry (XSD), metarcode will return the catalog url : the URL of the uploaded XSD. You have to use this URL inside our XML. To upload this repository entry into metaqrcode you have 2 ways : 

#### Using Metaqrcode Website ####

* login to metaqrcode
* search in metaqrcode catalog for personData.xsd (previously uploaded XSD) and copy the catalog URL (you can also find it at https://www.metaqrcode.com/api/c/2)
* go to uplaod xml
* DO NOT specify the default catalog URL 
* insert previous XML in the textarea (verify and correct xsi:schemaLocation attribute)
* press upload xml
* you will see the qrcode of the uploaded xml
* you can use (by ie download) the generated qrcode

#### Consume Metaqrcode REST API ####

* login to metaqrcode
* search in metaqrcode catalog for personData.xsd and copy the catalog URL (you can also find it at https://www.metaqrcode.com/api/c/2)

<pre>
	//
	// after login we can upload XML
	//
	var data = new FormData(); // create the form data to send within subsequent post
	var requestRepositoryUpload = new Object(); // create the upload xml request 
	requestRepositoryUpload.defaultCatalog=null; // DO NOT set the catalog URL the xml is referring to
	requestRepositoryUpload.correlationId=%%application correlation id%%; // if you need to search this qrcode in the future you can use this (your) correlationId. This is optional
	data.append('request', new Blob([JSON.stringify(requestRepositoryUpload)], {
	    type: "application/json"
	})); // set the request as fitst parameter in the form data
	var blob = new Blob(<your xml here>], { type: "text/xml"}); // create a Blob with the xml
	data.append('xml',blob); // set the xml as second parameter in the form data (in alternative you can use data.append('xml',<file field>[0].files[0]);
	$.ajax({
		  type: "POST", // method MUST BE post
		  url: "https://www.metaqrcode.com/api/rest/json/repository/upload", // upload URL
		  data: data, // formdata object
		  cache: false, 
		  contentType: false, 
		  processData: false,
		  beforeSend: function (request) {
			// use one of two methods depending on login type used
			  if (%%sessionToken%%!=null) {
		              request.setRequestHeader("Authorization", "Token "+%%sessionToken%%);
			  }
			  if (%%accessToken%%!=null) {
		              request.setRequestHeader("Authorization", "Bearer "+%%accessToken%%);
			  }
	          },
		  error: function(jqXHR, textStatus, errorThrown) {
			// error handling 
		  },
		  success: function(data, textStatus, jqXHR) {
			if (data.returnCode >= 0) { // verify that the response is ok
				data.repositoryGet; // the link to get your XML
				data.qrcodeGet; // *the link to get your QRCODE*   
			} else {
				// error handling 
			}
		  } 
		}); // send ajax POST request
</pre>

As you can see this example is the same of the hello world. In this case we do not specify the requestRepositoryUpload.defaultCatalog because the repository entry (XML) have to specify the catalog entry (XSD) reference. 

## 5.3. complex catalog entry (XSD) ##

In catalog entry (XSD) you can use all feature you need. In next example we use include and extension.
Suppose you want to extend previous XSD in this way : 

	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	<xs:schema version="1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
		xmlns="personData.xsd"
		targetNamespace="personData.xsd"
		elementFormDefault="qualified" attributeFormDefault="unqualified">
	  <xs:include schemaLocation="https://www.metaqrcode.com/api/c/2" />
	  <xs:element name="customer" type="customer"/>
	  <xs:complexType name="customer">
	    <xs:complexContent>
	      <xs:extension base="personData">
	        <xs:sequence>
	          <xs:element name="customerId" type="xs:long"/>
	          <xs:element name="customerType" type="customerType" minOccurs="0"/>
	          <xs:any processContents="lax" namespace="##other" minOccurs="0" maxOccurs="unbounded"/>
	        </xs:sequence>
	      </xs:extension>
	    </xs:complexContent>
	  </xs:complexType>
	  <xs:simpleType name="customerType">
	    <xs:restriction base="xs:string">
	      <xs:enumeration value="EASY"/>
	      <xs:enumeration value="PRO"/>
	      <xs:enumeration value="NORMAL"/>
	      <xs:enumeration value="TOP"/>
	    </xs:restriction>
	  </xs:simpleType>
	</xs:schema>

You can upload catalog entry (XSD) as shown in 4.1 example. You only have to pay attention to schemaLocation attribute of xs:include tag (by ie you can specify http://www.metaqrcode/api/c/2).
When you refer to a catalogEntry you always need to use the catalog URL.

## 5.4. repository entry (XML) referring different catalog entries (XSDs) ##

Suppose you have also this XSD : 

	<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	<xs:schema version="1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
		xmlns="intolerances.xsd"
		targetNamespace="intolerances.xsd"
		elementFormDefault="qualified" attributeFormDefault="unqualified">
	
	  <xs:element name="intolerances" type="intolerances"/>
	
	  <xs:complexType name="intolerances">
	    <xs:sequence>
	      <xs:element name="gluten" type="xs:boolean"/>
	      <xs:element name="milk" type="xs:boolean"/>
	    </xs:sequence>
	  </xs:complexType>
	</xs:schema>

So we can create following XML : 

	<?xml version="1.0" encoding="UTF-8"?>
	<customer xmlns="personData.xsd"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
			xsi:schemaLocation="personData.xsd https://www.metaqrcode.com/api/c/3
			intolerances.xsd https://www.metaqrcode.com/api/c/4" 
			xmlns:i="intolerances.xsd">
	  <firstName>firstName</firstName>
	  <lastName>lastName</lastName>
	  <sex>MALE</sex>
	  <birthday>2001-12-31T12:00:00</birthday>
	  <birthPlace>birthPlace</birthPlace>
	  <citizenship>citizenship</citizenship>
	  <address>address</address>
	  <eMail>eMail</eMail>
	  <taxCode>taxCode</taxCode>
	  <phone>phone</phone>
	  <customerId>1</customerId>
	  <customerType>PRO</customerType>
	  <i:intolerances>
	  	<i:gluten>true</i:gluten>
	  	<i:milk>false</i:milk>
	  </i:intolerances>
	</customer>

You can upload this repository entry (XML) as shown in 4.2, you only have to pay attention to xsi:noNamespaceSchemaLocation and xmlns:i attributes of root tag.

## 6. using qrcode ##

After repository entry (XML) upload you can get the QRCODE corresponding to your xml using data.qrcodeGet (as shown previously).

QRCODE is generated as image in PNG format. The dimension of the qrcode is based on this http://www.qrstuff.com/blog/2011/11/23/qr-code-minimum-size

Size of generated qrcode is 210px x 210px because we suppose that you will print it in minimum 300dpi resolution. If you print with this resolution you will have a qrcode of 0,7x0,7 inch (18x18 mm). You can resize the PNG received depending on quality of your print. If you print using a 600dpi resolution, you can print your qrcode using half dimensions (0,35x0,35 inch -> 9x9 mm).

These are only theoretical calculations, in the real world you have to do some experiment to check best result in your scenario.To verify result of your metaqrcode print you can use many app. The first you can use is the zxing mobile app : https://play.google.com/store/apps/details?id=com.google.zxing.client.android. Zxing is an opensource project (https://github.com/zxing, https://zxingnet.codeplex.com/) available in many technologies (java, .net, etc.).

## 7. REST API descriptors ##

All metaqrcode REST API can be consumed using JSON or XML.
You can find Java DTO used by following rest services here : https://github.com/JENIASoftware/metaqrcode/tree/master/metaqrcode-dto

#### JAVA SAMPLES ####

You can find some sample written in java here : https://github.com/JENIASoftware/metaqrcode/tree/master/metaqrcode-client/metaqrcode-client-java

#### JAVASCRIPT SAMPLES ####

You can find some sample written in javascript here : https://github.com/JENIASoftware/metaqrcode/tree/master/metaqrcode-client/metaqrcode-client-js

#### XML URL ####

https://www.metaqrcode.com/api/rest/xml/ping?_wadl

https://www.metaqrcode.com/api/rest/xml/catalog?_wadl

https://www.metaqrcode.com/api/rest/xml/repository?_wadl

https://www.metaqrcode.com/api/rest/xml/link?_wadl

https://www.metaqrcode.com/api/rest/xml/registration?_wadl

https://www.metaqrcode.com/api/rest/xml/login?_wadl

#### JSON URL ####

https://www.metaqrcode.com/api/rest/json/ping?_wadl

https://www.metaqrcode.com/api/rest/json/catalog?_wadl

https://www.metaqrcode.com/api/rest/json/repository?_wadl

https://www.metaqrcode.com/api/rest/json/link?_wadl

https://www.metaqrcode.com/api/rest/json/registration?_wadl

https://www.metaqrcode.com/api/rest/json/login?_wadl

#### other URL ####

https://www.metaqrcode.com/api/c/{id} -> (http GET) download catalog entry (XSD) by id

https://www.metaqrcode.com/api/c/{id}/detail -> (http GET) download catalog entry detail by id

https://www.metaqrcode.com/api/r/{id} -> (http GET) download repository entry (XML) by id (** _this is contained in qrcode_ **)

https://www.metaqrcode.com/api/r/{id}/json -> (http GET) download repository entry (XML) as json by id

https://www.metaqrcode.com/api/r/{id}/detail -> (http GET) download repository entry details by id

https://www.metaqrcode.com/api/qr/{id} -> (http GET) download qrcode (image) of a repository entry id

https://www.metaqrcode.com/api/l/{oc} -> (http GET) download repository entry (XML) by his link

https://www.metaqrcode.com/api/l/{oc}/json -> (http GET) download repository entry (XML) as json his link

https://www.metaqrcode.com/api/l/{oc}/detail -> (http GET) download repository entry details by his link







