package com.goyourfly.weather_library.net.yahoo_obj;

/**
 * Created by gaoyf on 14/12/29.
 */
public class NetPlace {
    public int woeid;
    public String placeTypeName;
    public String name;
    public String country;
    public String admin1;
    public String admin2;
    public String admin3;
    public String locality1;
    public String locality2;
    public String postal;
    public NetCentroid centroid;
    public class boundingBox{
        public NetCentroid sounthWest;
        public NetCentroid northEast;
    }

    public int areaRank;
    public int popRank;
    public String timezone;
    public String url;
    public String lang;

//{"places":{"place":[{"woeid":2151330,"placeTypeName":"Town","placeTypeName attrs":{"code":7},"name":"Beijing","country":"China","country attrs":{"type":"Country","code":"CN","woeid":23424781},"admin1":"Beijing","admin1 attrs":{"type":"Municipality","code":"CN-11","woeid":12578011},"admin2":"Beijing","admin2 attrs":{"type":"Prefecture","code":"","woeid":12686916},"admin3":"","locality1":"Beijing","locality1 attrs":{"type":"Town","woeid":2151330},"locality2":"","postal":"1000","postal attrs":{"type":"Postal Code","woeid":12712251},"centroid":{"latitude":39.90601,"longitude":116.387909},"boundingBox":{"southWest":{"latitude":39.689602,"longitude":116.105789},"northEast":{"latitude":40.12241,"longitude":116.670021}},"areaRank":7,"popRank":13,"timezone":"Asia\/Shanghai","timezone attrs":{"type":"Time Zone","woeid":56043597},"uri":"http:\/\/where.yahooapis.com\/v1\/place\/2151330","lang":"en-US"}],"start":0,"count":1,"total":5}}


//{"places":{"place":[{"woeid":2151330,"placeTypeName":"WOE_TOWN","placeTypeName attrs":{"code":7},"name":"\u5317\u4eac\u5e02","country":"\u4e2d\u56fd","country attrs":{"type":"WOE_COUNTRY","code":"CN","woeid":23424781},"admin1":"\u5317\u4eac\u76f4\u8f96\u5e02","admin1 attrs":{"type":"Sheng","code":"","woeid":12578011},"admin2":"\u5317\u4eac","admin2 attrs":{"type":"\u90fd\u9053\u5e9c\u770c","code":"","woeid":12686916},"admin3":"","locality1":"\u5317\u4eac\u5e02","locality1 attrs":{"type":"WOE_TOWN","woeid":2151330},"locality2":"","postal":"1000","postal attrs":{"type":"WOE_ZIP","woeid":12712251},"centroid":{"latitude":39.90601,"longitude":116.387909},"boundingBox":{"southWest":{"latitude":39.689602,"longitude":116.105789},"northEast":{"latitude":40.12241,"longitude":116.670021}},"areaRank":7,"popRank":13,"timezone":"Asia\/Shanghai","timezone attrs":{"type":"WOE_TIMEZONE","woeid":56043597},"uri":"http:\/\/where.yahooapis.com\/v1\/place\/2151330","lang":"zh-CN"}],"start":0,"count":1,"total":5}}
}
