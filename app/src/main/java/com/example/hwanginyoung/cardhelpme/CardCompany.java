package com.example.hwanginyoung.cardhelpme;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class CardCompany extends AppCompatActivity {

    protected  String[] cardCompanys = {"농협카드", "신한카드","우리카드","하나카드","BC카드","KB국민","IBK카드","수협카드","카카오카드"};
    protected Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardcompany);

        ListView cardList = (ListView)findViewById(R.id.company_list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cardCompanys);

        cardList.setAdapter(adapter);

        cardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int item, long l) {
                Object object = (Object)adapterView.getAdapter().getItem(item);
                Toast.makeText(getApplicationContext(), "내용은 : ."+item, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "내용은 : ."+object, Toast.LENGTH_SHORT).show();


                   switch (item){
                       case 0:
                            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:1588-2100"));
                            break;
                       case 1:
                            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:1588-7200"));
                            break;
                       case 2:
                            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:1588-9955"));
                            break;
                       case 3:
                            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:1599-1111"));
                            break;
                       case 4:
                            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:1588-4515"));
                            break;
                       case 5:
                            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:1588-1688"));
                            break;
                       case 6:
                           intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:1566-2566"));
                           break;
                       case 7:
                           intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:1588-1515"));
                           break;
                       case 8:
                           intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:1599-3333"));
                           break;

                        default:
                           return;

                }
                startActivity(intent);
            }
        });




    }
}
