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



