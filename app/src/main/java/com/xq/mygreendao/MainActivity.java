package com.xq.mygreendao;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.xq.mygreendao.db.entry.User;
import com.xq.mygreendao.db.gen.UserDao;
import com.xq.mygreendao.db.util.DbManager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_Name, et_age, et_sex, et_Salary;
    private Button btn_insert, btn_query, btn_queryAll, btn_delete, btn_update;
    private ListView mListView;
    private String name, sex, salary, age;
    private MyAdapter myAdapter;
    private List<User> userlists = new ArrayList<>();
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDao = DbManager.getDaoSession().getUserDao();

        findViews();
        setListener();

        /*初始化数据*/
        userlists = userDao.loadAll();
        myAdapter = new MyAdapter(this, userlists);
        mListView.setAdapter(myAdapter);
    }

    private void setListener() {
        btn_insert.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        btn_queryAll.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_update.setOnClickListener(this);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder alerDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                alerDialogBuilder.setPositiveButton("修改", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        name = et_Name.getText().toString().trim();
                        age = et_age.getText().toString();
                        sex = et_sex.getText().toString().trim();
                        salary = et_Salary.getText().toString().trim();
                        //修改单条数据
                        User updateData = new User(userlists.get(position).getId(), name, age, sex, salary);
                        userDao.update(updateData);
                        myAdapter.setData(userDao.loadAll());
                        dialog.dismiss();
                    }
                });

                alerDialogBuilder.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User user = userlists.get(position);
                        //删除单条数据
                        userDao.delete(user);
                        myAdapter.setData(userDao.loadAll());
                        dialog.dismiss();
                    }
                });

                alerDialogBuilder.create();
                alerDialogBuilder.show();
                return false;
            }
        });
    }

    private void findViews() {
        et_Name = (EditText) findViewById(R.id.et_name);
        et_age = (EditText) findViewById(R.id.et_age);
        et_sex = (EditText) findViewById(R.id.et_sex);
        et_Salary = (EditText) findViewById(R.id.et_salary);

        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_query = (Button) findViewById(R.id.btn_query);
        btn_queryAll = (Button) findViewById(R.id.btn_queryAll);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_update = (Button) findViewById(R.id.btn_update);
        mListView = (ListView) findViewById(R.id.list);
    }

    @Override
    public void onClick(View view) {
        name = et_Name.getText().toString().trim();
        age = et_age.getText().toString();
        sex = et_sex.getText().toString().trim();
        salary = et_Salary.getText().toString().trim();
        switch (view.getId()) {
            case R.id.btn_insert:
                insert();
                break;

            case R.id.btn_query://条件查询
                query();
                break;

            case R.id.btn_queryAll://全部查询
                queryAll();
                break;

            case R.id.btn_update:
                userDao.deleteByKey(1L);
                myAdapter.setData(userDao.loadAll());
                break;

            case R.id.btn_delete:
                deleteAll();
                break;
        }
    }

    private void deleteAll() {
        userDao.deleteAll();
        myAdapter.setData(userDao.loadAll());
    }

    private void queryAll() {
        List<User> queryAllUsers = userDao.loadAll();

        userlists.clear();
        for (int i = 0; i < queryAllUsers.size(); i++) {
            userlists.add(queryAllUsers.get(i));
        }
        myAdapter.notifyDataSetChanged();
    }

    private void query() {
        List<User> queryList = userDao.queryBuilder()
                .where(UserDao.Properties.Name.eq(name))
                .list();

        userlists.clear();
        for (int i = 0; i < queryList.size(); i++) {
            userlists.add(queryList.get(i));
        }
        myAdapter.notifyDataSetChanged();
    }

    private void insert() {
        User user = new User(null, name, age, sex, salary);
        userDao.insert(user);//插入数据库

        myAdapter.addData(user);//更新列表
    }
}
