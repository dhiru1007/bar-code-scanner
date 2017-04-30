var quality = 60; 
var timeout = 10000; 
var isoTemplate;
function GetInfo() {
    document.getElementById('tdSerial').innerHTML = "";
    document.getElementById('tdCertification').innerHTML = "";
    document.getElementById('tdMake').innerHTML = "";
    document.getElementById('tdModel').innerHTML = "";
    document.getElementById('tdWidth').innerHTML = "";
    document.getElementById('tdHeight').innerHTML = "";
    
    var key = document.getElementById('txtKey').value;
    
    var res;
    if (key.length === 0) {
        res = GetMFS100Info();
    }
    else {
        res = GetMFS100KeyInfo(key);
    }
    if (res.httpStaus) {

        document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;

        if (res.data.ErrorCode == "0") {
            document.getElementById('tdSerial').innerHTML = res.data.DeviceInfo._SerialNo;
           // document.getElementById('tdCertification').innerHTML = res.data.DeviceInfo.Certificate;
            document.getElementById('tdMake').innerHTML = res.data.DeviceInfo._Make;
            document.getElementById('tdModel').innerHTML = res.data.DeviceInfo._Model;
            document.getElementById('tdWidth').innerHTML = res.data.DeviceInfo._Width;
            document.getElementById('tdHeight').innerHTML = res.data.DeviceInfo._Height;
        }
    }
    else {
        alert(res.err);
    }
    return false;
}

function Capture() {
    try {
        document.getElementById('txtStatus').value = "";
        document.getElementById('imgFinger').src = "data:image/bmp;base64,";
        document.getElementById('txtImageInfo').value = "";
      //  document.getElementById('txtIsoTemplate').value = "";

        var res = CaptureFinger(quality, timeout);
		//alert(JSON.stringify(res));
        if (res.httpStaus) {

            document.getElementById('txtStatus').value = "ErrorCode: " + res.data.ErrorCode + " ErrorDescription: " + res.data.ErrorDescription;

            if (res.data.ErrorCode == "0") {
                document.getElementById('imgFinger').src = "data:image/bmp;base64," + res.data.BitmapData;
                var imageinfo = "Quality: " + res.data.Quality + " Nfiq: " + res.data.Nfiq; 
//+ " W(in): " + res.data.InWidth + " H(in): " + res.data.InHeight + " area(in): " + res.data.InArea + " Resolution: " + res.data.Resolution + " GrayScale: " + res.data.GrayScale + " Bpp: " + res.data.Bpp + " WSQCompressRatio: " + res.data.WSQCompressRatio + " WSQInfo: " + res.data.WSQInfo;
                document.getElementById('txtImageInfo').value = imageinfo;
				isoTemplate = res.data.IsoTemplate;
              //  document.getElementById('txtIsoTemplate').value = res.data.IsoTemplate;
            }
        }
        else {
            alert(res.err);
        }
    }
    catch (e) {
        alert(e);
    }
    return false;
}

function Match() {
    try {
        //var isoTemplate = document.getElementById('txtIsoTemplate').value;
        var res = MatchFinger(quality, timeout, isoTemplate);
		//alert(JSON.stringify(res));
        if (res.httpStaus) {
            if (res.data.status) {
                alert("Finger matched");
            }
            else {
                if (res.data.ErrorCode != "0") {
                    alert(res.data.ErrorDescription);
                }
                else {
                    alert("Finger not matched");
                }
            }
        }
        else {
            alert(res.err);
        }
    }
    catch (e) {
        alert(e);
    }
    return false;
}
       
