<%-- 
    Document   : map
    Created on : 2014-12-15, 02:08:50
    Author     : Bartosz Batorek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            html { height: 100% }
            body { height: 95%; margin: 10px; padding: 0px }
            #mapka { height: 100% }
        </style>
        <title>TC65 Localizer Route</title>
        <script src="http://maps.google.com/maps/api/js?sensor=false" type="text/javascript"></script>
        <script type="text/javascript" >

            function createXmlHttpRequest() {
                try {
                    if (typeof ActiveXObject != 'undefined') {
                        return new ActiveXObject('Microsoft.XMLHTTP');
                    } else if (window["XMLHttpRequest"]) {
                        return new XMLHttpRequest();
                    }
                } catch (e) {
                    changeStatus(e);
                }
                return null;
            }
            ;

            /**
             * This functions wraps XMLHttpRequest open/send function.
             * It lets you specify a URL and will call the callback if
             * it gets a status code of 200.
             * @param {String} url The URL to retrieve
             * @param {Function} callback The function to call once retrieved.
             */
            function downloadUrl(url, callback) {
                var status = -1;
                var request = createXmlHttpRequest();
                if (!request) {
                    return false;
                }

                request.onreadystatechange = function () {
                    if (request.readyState == 4) {
                        try {
                            status = request.status;
                        } catch (e) {
                            // Usually indicates request timed out in FF.
                        }
                        if ((status == 200) || (status == 0)) {
                            callback(request.responseText, request.status);
                            request.onreadystatechange = function () {
                            };
                        }
                    }
                }
                request.open('GET', url, true);
                try {
                    request.send(null);
                } catch (e) {
                    changeStatus(e);
                }
            }
            ;

            /**
             * Parses the given XML string and returns the parsed document in a
             * DOM data structure. This function will return an empty DOM node if
             * XML parsing is not supported in this browser.
             * @param {string} str XML string.
             * @return {Element|Document} DOM.
             */
            function xmlParse(str) {
                if (typeof ActiveXObject != 'undefined' && typeof GetObject != 'undefined') {
                    var doc = new ActiveXObject('Microsoft.XMLDOM');
                    doc.loadXML(str);
                    return doc;
                }

                if (typeof DOMParser != 'undefined') {
                    return (new DOMParser()).parseFromString(str, 'text/xml');
                }

                return createElement('div', null);
            }

            /**
             * Appends a JavaScript file to the page.
             * @param {string} url
             */
            function downloadScript(url) {
                var script = document.createElement('script');
                script.src = url;
                document.body.appendChild(script);
            }

        </script>

    </head>
    <body onload='initialize()'>
        <script type='text/javascript'>

            function initialize()
            {
                var wspolrzedne = new google.maps.LatLng(50.288896, 18.67430);
                var opcjeMapy = {
                    zoom: 11,
                    center: wspolrzedne,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                var mapa = new google.maps.Map(document.getElementById("mapka"), opcjeMapy);

                downloadUrl('/TC65Server/GetXml.do?uid=<%= request.getParameter("uid")%>', function (dane)
                {

                    var xmlDoc = xmlParse(dane);
                    var polilinie = xmlDoc.documentElement.getElementsByTagName('trasa');
                    for (var i = 0; i < polilinie.length; i++)
                    {
                        var punkty = polilinie[i].getElementsByTagName("punkt");
                        var punkty_polilinia = [];
                        for (var j = 0; j < punkty.length; j++)
                        {
                            var lat = parseFloat(punkty[j].getAttribute("lat"));
                            var lon = parseFloat(punkty[j].getAttribute("lon"));
                            punkty_polilinia.push(new google.maps.LatLng(lat, lon));
                        }

                        var rysuj = new google.maps.Polyline({
                            path: punkty_polilinia,
                            strokeColor: "#FF0000",
                            strokeOpacity: 1.0,
                            strokeWeight: 5
                        });
                        rysuj.setMap(mapa);
                    }

                    var rozmiar_i = new google.maps.Size(32, 32);
                    var punkt_startowy = new google.maps.Point(0, 0);
                    var punkt_zaczepienia = new google.maps.Point(16, 16);
                    var ikona1 = new google.maps.MarkerImage("http://www.google.com/intl/en_ALL/mapfiles/dd-start.png", rozmiar_i, punkt_startowy, punkt_zaczepienia);
                    var ikona2 = new google.maps.MarkerImage("http://www.google.com/intl/en_ALL/mapfiles/dd-end.png", rozmiar_i, punkt_startowy, punkt_zaczepienia);

                    var wsp_start = new google.maps.LatLng(parseFloat(punkty[1].getAttribute("lat")), parseFloat(punkty[1].getAttribute("lon")));
                    var wsp_stop = new google.maps.LatLng(parseFloat(punkty[punkty.length - 1].getAttribute("lat")), parseFloat(punkty[punkty.length - 1].getAttribute("lon")));
                    var opcjeMarkera1 = {
                        position: wsp_start,
                        map: mapa,
                        title: 'Start',
                        icon: ikona1
                    }
                    var opcjeMarkera2 = {
                        position: wsp_stop,
                        map: mapa,
                        title: 'Stop',
                        icon: ikona2
                    }
                    var marker1 = new google.maps.Marker(opcjeMarkera1);
                    var marker2 = new google.maps.Marker(opcjeMarkera2);

                    wspolrzedne2 = new google.maps.LatLng(parseFloat(punkty[1].getAttribute("lat")), parseFloat(punkty[1].getAttribute("lon")));
                    mapa.setCenter(wspolrzedne2);
                });
            }
        </script>

        <div id="mapka" style="width: 100%; height: 97%; border: 1px solid black; background: gray;">
            <!-- TU WSTAWI SIE MAPA -->
        </div>

        <br>
        <a href="ShowRoutes.do">Wróć</a>
        <br><br>

    </body>
</html>
