# metaqrcode getting started

## 1. hello world! ##
Using metaqrcode you can upload your XML document and associate it to a qrcode. I hope this is clear!
As basic example of metaqrcode usage, you can try to upload an helloworld document.
This is the xml document we want to upload:
    
    <?xml version="1.0" encoding="UTF-8"?>
    <helloWorld>
      <who>Andrea</who>
    </helloWorld>
You can upload it using graphcal interface (our website) or using metaqrcode REST API.
To upload the helloworld xml document you have to :

- login to metaqrcode
- search in metaqrcode catalog for helloworld.xsd and copy the catalog URL
- go to uplaod xml
- specify the default catalog URL with the url of the catalog of helloworld.xsd
- insert previous XML in the textarea
- press upload xml
- you will see the qrcode of the uploaded xml
- you can use the generated qrcode (by ie download) 

###Consume Metaqrcode REST API ###
- login to metaqrcode (using login REST API or openidconnect, see below)
- search in metaqrcode catalog for helloworld.xsd and copy the catalog URL


	    // after login we can upload XML
    	//
    	var data = new FormData(); // create the form data to send within subsequent post
    	var requestRepositoryUpload = new Object(); // create the upload xml request 
    	requestRepositoryUpload.defaultCatalog=<catalog url>; // set the catalog URL the xml is referring to
    	requestRepositoryUpload.correlationId=<application correlation id>; // if you need to search this qrcode in the future you can use this (your) correlationId. This is optional
    	data.append('request', new Blob([JSON.stringify(requestRepositoryUpload)], {
    	type: "application/json"
    	})); // set the request as fitst parameter in the form data
    	var blob = new Blob(<your xml here>], { type: "text/xml"}); // create a Blob with the xml
    	data.append('xml',blob); // set the xml as second parameter in the form data (in alternative you can use data.append('xml',<file field>[0].files[0]);
    	$.ajax({
    		  type: "POST", // method MUST BE post
    		  url: "http://www.metaqrcode.com/api/rest/json/repository/upload", // XML upload URL
    		  data: data, // formdata object
    		  cache: false, 
    		  contentType: false, 
    		  processData: false,
    		  beforeSend: function (request) {
    			// use one of two methods depending on login type used
    			  if (<sessionToken>!=null) {
    		  request.setRequestHeader("Authorization", "Token "+<sessionToken>);
    			  }
    			  if (<accessToken>!=null) {
    		  request.setRequestHeader("Authorization", "Bearer "+<accessToken>);
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


## Basic Concepts ##
Using metaqrcode you can upload your XML document and associate it to a qrcode. I hope this is clear!
An XML document into metaqrcode is called Repository Entry. In metaqrcode the repository is the container of all repository entry, then the repository is the container of all XML documents. 
Each XML document inside metaqrcode has to be associated with at least an XSD. 
In metaqrcode an XSD is called Catalog Entry. The Catalog is the container of all XSD.

When you upload an XML you have to specify one or more XSD. The XML have to comply with the given XSD. Metaqrcode will check compliance during upload phase.

When you upload an XML you can specify one XSD using API (outside the XML) or you can specify one or more XSD inside the XML document.

That's all relevants concepts of metaqrcode. 
## Authentication/Authorization ##
Using metaqrcode you can upload your XML document and associate it to a qrcode. I hope this is clear!
Metaqrcode expose rest json and rest xml API. Some API needs authentication to proceed.
To authenticate to metaqrcode you can use two methods : login API or openidconnect.
In every scenario you (developer) need to register yourself and the app you are developing on metaqrcode openidconnect server.
Go to [http://www.metaqrcode.com/oidc](http://www.metaqrcode.com/oidc) to register yourself and your app. After client (app) registration you will obtain a client_id to use in login API.

###Get the metaqrcode session token###
	// 
	// logging in using REST API
	//
	var requestLogin = new Object(); // create the login request object
	requestLogin.email=<the email address>; // set the userId (email) to be logged in. The user must be registered into metaqrcode
	requestLogin.password=<password>; // set the user password
	requestLogin.clientId=<client_id>; // client_id of the app is using metaqrcode
	$.ajax({
		  type: "POST", // MUST BE POST
		  url: "http://www.metaqrcode.com/api/rest/json/login/login", // login URL, will be https
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

###Using OpenID Connect###

**authorization_endpoint**: [http://www.metaqrcode.com/oidc/authorize](http://www.metaqrcode.com/oidc/authorize)

For more info see our configuration endpoint: [http://www.metaqrcode.com/oidc/.well-known/openid-configuration](http://www.metaqrcode.com/oidc/.well-known/openid-configuration)

*Note that uploaded XML are "property of" the user has uploaded them. We discourage using one generic user and password for all users of your app.
*We also discourage use of login REST API. The preferred method to login users is openidconnect.*
You can register user inside metaqrcode using REST API, but you should never save userid and password of your users.*

##Working with catalog##
Suppose you want to upload this XSD

    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <xs:schema version="1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema">
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

You can upload it using graphcal interface (our website) or using metaqrcode REST API.
To upload the XSD you have to : 

- login to metaqrcode
- go to catalog 
- press up arrow (upload new catalog)
- set a name for your XSD (by ie personData.xsd)
- set a description for your XSD
- insert previous XSD in the textarea
- press upload button

To use metaqrcode REST API you have to (we suppose you use jquery):

	//
	// after login we can upload XSD
	//
	// we have to send the json request and the XSD, to do that we have to create a formdata
	var data = new FormData();
	var requestCatalogUpload = new Object();
	requestCatalogUpload.name=<name of the catalog to be uploaded>;
	requestCatalogUpload.description=<description of the catalog>;
	// in the form we put two attribute : request -> the json request
	data.append('request', new Blob([JSON.stringify(requestCatalogUpload)], {
	    type: "application/json"
	}));
	// and xsd -> the catalog entry (XSD) to be uploaded
	var blob = new Blob(<your xsd here>], { type: "text/xml"}); // create a Blob with the xsd
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
			  if (<sessionToken>!=null) {
		              request.setRequestHeader("Authorization", "Token "+<sessionToken>);
			  }
			  if (<accessToken>!=null) {
		              request.setRequestHeader("Authorization", "Bearer "+<accessToken>);
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

