package com.example.helloworld;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class BusListMainActivity extends AppCompatActivity {
    EditText edit_station;
    EditText edit_other;    //노선 아이디 등
    TextView text;
//    TextView text_1;      //@1
    XmlPullParser xpp;

    String key="h6nv4lK9Cv3Anb96btTQBHJLS3mk28kTbm3s%2BjyuoWIXpZ9s2H0jHqOaOA1OTb2c979KZfVibugPdBXRvUY92w%3D%3D";
//    String data;          //@1
    String locationNo1= new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buslist_main);

        edit_station= (EditText)findViewById(R.id.edit_station);
        edit_other= (EditText)findViewById(R.id.edit_other);

        //
        //
//        text= (TextView)findViewById(R.id.result);  //버스 정보 리스트로 출력, 구현은 주석 처리 @1로 표시

    }
    public void mOnClick(View v){
        switch (v.getId()){
            case R.id.button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        data=getXmlData();    //@1
                        locationNo1 = getLocationNo1();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                text.setText(data);   //@1
                                noti("몇 정거장 전에 알릴지 입력, 함수 수정 필요",locationNo1);
                            }
                        });
                    }
                }).start();
                break;
        }
    }

    //@1
    /*



    String getXmlData(){
        StringBuffer buffer=new StringBuffer();
        String stationID = edit_station.getText().toString();//EditText에 작성된 Text얻어오기

        String queryUrl="http://apis.data.go.kr/6410000/busarrivalservice/getBusArrivalList?serviceKey=h6nv4lK9Cv3Anb96btTQBHJLS3mk28kTbm3s%2BjyuoWIXpZ9s2H0jHqOaOA1OTb2c979KZfVibugPdBXRvUY92w%3D%3D&stationId="
                +stationID;
        try{
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType= xpp.getEventType();
            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//테그 이름 얻어오기
                        if(tag.equals("busArrivalList")) ;// 첫번째 검색결과
                        else if(tag.equals("locationNo1")){
                            buffer.append("첫번째 차량 위치 : ");
                            xpp.next();
                            buffer.append(xpp.getText());//category 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("predictTime1")){
                            buffer.append("첫번째 차량 도착예상시간: ");
                            xpp.next();
                            buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //테그 이름 얻어오기

                        if(tag.equals("busArrivalList")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈
                        break;
                }

                eventType= xpp.next();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        buffer.append("목록 끝\n");
        return buffer.toString();//StringBuffer 문자열 객체 반환

    }//getXmlData








    */

    String getLocationNo1(){
        StringBuffer buffer=new StringBuffer();
        String stationID = edit_station.getText().toString();//EditText에 작성된 Text얻어오기

        String queryUrl="http://apis.data.go.kr/6410000/busarrivalservice/getBusArrivalList?serviceKey=h6nv4lK9Cv3Anb96btTQBHJLS3mk28kTbm3s%2BjyuoWIXpZ9s2H0jHqOaOA1OTb2c979KZfVibugPdBXRvUY92w%3D%3D&stationId="
                +stationID;
        try{
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType= xpp.getEventType();
            while( eventType != XmlPullParser.END_DOCUMENT && locationNo1.length()==0){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//테그 이름 얻어오기
                        if(tag.equals("busArrivalList")) ;// 첫번째 검색결과
                        else if(tag.equals("locationNo1")){
                            xpp.next();
                            buffer.append(xpp.getText());//category 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append(" ");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //테그 이름 얻어오기

                        if(tag.equals("busArrivalList"));// 첫번째 검색결과종료..줄바꿈
                        break;
                }

                eventType= xpp.next();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return buffer.toString();//StringBuffer 문자열 객체 반환

    }//getXmlData method....



    //알림
    public void createNotification(View view) {
        //show();
    }

    private void show(String notiText) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("귀갓길");
        builder.setContentText(notiText);

        Intent intent = new Intent(this, BusListMainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //이미 실행되어 있으면 내용을 업데이트 해줌

        builder.setContentIntent(pendingIntent);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(largeIcon);

        builder.setColor(Color.RED);

        Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(ringtoneUri);

        long[] vibrate = {0,100,200,300};   //진동 패턴
        builder.setVibrate(vibrate);
        builder.setAutoCancel(true);    //노티를 클릭했을 때도 노티ㅇㅇ 아니면 false

        //오레오에서 채널 등록 필요
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE); //%%과 같은코드
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel("default", "기본 채널",
                    NotificationManager.IMPORTANCE_DEFAULT));
        }
        manager.notify(1, builder.build());
    }

    public void removeNotification(View view) {
        hide();
    }

    private void hide() {
        NotificationManagerCompat.from(this).cancel(1); //%%과 같은코드, 1번 노티 제거
    }

    public void noti(String in,String locationNo1){        //in : input(locationNo1)
        in="2"; //수정시 지울 문장
        String location=locationNo1;
        String[] str = location.split(" ");

       // if(str[0]==in)
            show("버스가 "+str[0]+" 정류장 전입니다.");
    }

}