package com.example.th5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class bai2 extends AppCompatActivity {
    ListView lstview;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    SQLiteDatabase mydatabase;
    DBHelper dbHelper;
    EditText editTen,editCMND,editBosung;
    CheckBox chkdocbao,chkdocsach,chkdoccode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);

        lstview = findViewById(R.id.lstview);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lstview.setAdapter(myadapter);

        dbHelper = new DBHelper(this);
        mydatabase = dbHelper.getWritableDatabase();

        editTen=(EditText) findViewById(R.id.editHoten);
        editCMND=(EditText) findViewById(R.id.editCMND);
        editBosung=(EditText) findViewById(R.id.editBosung);
        chkdocbao=(CheckBox) findViewById(R.id.chkdocbao);
        chkdoccode=(CheckBox) findViewById(R.id.chkdoccoding);
        chkdocsach=(CheckBox) findViewById(R.id.chkdocsach);
        Button btn=(Button) findViewById(R.id.btnguitt);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                doShowInformation();

                String hoten = editTen.getText().toString().trim();
                String cmnd = editCMND.getText().toString().trim();
                String bosung = editBosung.getText().toString().trim();

                String bang="";
                RadioGroup group=(RadioGroup) findViewById(R.id.radioGroup1);
                int id=group.getCheckedRadioButtonId();
                RadioButton rad=(RadioButton) findViewById(id);
                bang=rad.getText()+"";

                String sothich="";

                if(chkdocbao.isChecked()){
                    sothich+=chkdocbao.getText()+"\n";
                }
                if(chkdocsach.isChecked()) {
                    sothich += chkdocsach.getText() + "\n";
                }
                if(chkdoccode.isChecked()) {
                    sothich += chkdoccode.getText() + "\n";
                }

                ContentValues myvalue = new ContentValues();
                myvalue.put(DBHelper.NAME, hoten);
                myvalue.put(DBHelper.CMND, cmnd);
                myvalue.put(DBHelper.BANGCAP, bang);
                myvalue.put(DBHelper.SOTHICH, sothich);
                myvalue.put(DBHelper.THONGTINBOSUNG, bosung);

                long result = mydatabase.insert(DBHelper.TABLE_NAME, null, myvalue);
                if (result == -1) {
                    Toast.makeText(bai2.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(bai2.this, "Đã thêm thành công!", Toast.LENGTH_SHORT).show();
                    editTen.setText("");
                    editCMND.setText("");
                    editBosung.setText("");


                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
    private void displayData() {
        Cursor c = mydatabase.query("IN4", null, null, null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            String data = c.getString(0) + " " + c.getString(1) + "  " + c.getString(2) + "  " + c.getString(3)+ "  " + c.getString(4);
            mylist.add(data);
            c.moveToNext();
        }
        c.close();
        myadapter.notifyDataSetChanged();
    }
    public void doShowInformation()
    {
        //Kiểm tra tên hợp lệ
        String ten=editTen.getText()+"";
        ten=ten.trim();
        if(ten.length()<3)
        {
            editTen.requestFocus();
            editTen.selectAll();
            Toast.makeText(this, "Tên phải >= 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }
        //kiểm tra CMND hợp lệ
        String cmnd=editCMND.getText()+"";
        cmnd=cmnd.trim();
        if(cmnd.length()!=9)
        {
            editCMND.requestFocus();
            editCMND.selectAll();
            Toast.makeText(this, "CMND phải đúng 9 ký tự", Toast.LENGTH_LONG).show();
            return;
        }
        //Kiểm tra bằng cấp
        String bang="";
        RadioGroup group=(RadioGroup) findViewById(R.id.radioGroup1);
        int id=group.getCheckedRadioButtonId();
        if(id==-1)
        {
            Toast.makeText(this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
            return;
        }
        RadioButton rad=(RadioButton) findViewById(id);
        bang=rad.getText()+"";
        //Kiểm tra sở thích
        String sothich="";
        if(chkdocbao.isChecked())
            sothich+=chkdocbao.getText()+"\n";
        if(chkdocsach.isChecked())
            sothich+=chkdocsach.getText()+"\n";
        if(chkdoccode.isChecked())
            sothich+=chkdoccode.getText()+"\n";
        if(sothich.isEmpty())
        {
            Toast.makeText(this, "Phải chọn ít nhất 1 sở thích ", Toast.LENGTH_LONG).show();
            return;
        }
        String bosung=editBosung.getText()+"";

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        //tạo nội dung
        String msg=ten+"\n";
        msg+= cmnd+"\n";
        msg+=bang+"\n";
        msg+=sothich;
        msg+="-----------------------------\n";
        msg+="Thông tin bổ sung:\n";
        msg+=bosung+ "\n";
        msg+="-----------------------------";
        builder.setMessage(msg);//thiết lập nội dung
        builder.create().show();//hiển thị Dialog
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}