package com.example.app;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class dashboard extends AppCompatActivity {

    RelativeLayout relativeLayout1, relativeLayout2, relativeLayout3, relativeLayout4;
    Gson gson = new Gson();
    TextView course_type;
    AlertDialog.Builder mBuildercourType;
    AlertDialog mDialogcourType;
    String sendcourId = "";
    Drop1Request drop1Request;
    List<drop1data> coursetype;
    String[] listItemscourType;
    boolean[] checkedItemscourType;
    HashMap<Integer, Boolean> selcourType = new HashMap<>();

    TextView branch_type;
    AlertDialog.Builder mBuilderbranchType;
    AlertDialog mDialoguebranchType;
    String sendbranchId= "";
    List<Drop2Request> drop2Requests;
    String[] listItemsbranchType;
    boolean[] checkedItemsbranchType;
    HashMap<Integer , Boolean> selbranchType =new HashMap<>();
    private ProgressDialog progressDialog;

    TextView sem_type;
    AlertDialog.Builder mBuildersemType;
    AlertDialog mDialoguesemType;
    String sendsemId="";
    Drop3Request drop3Request;
    List sem;
    String[] listItemsemType;
    boolean[] checkedItemsemType;
    HashMap<Integer, Boolean> selsemType= new HashMap<>();

    TextView sec_type;
    AlertDialog.Builder mBuildersecType;
    AlertDialog mDialogsecType;
    String sendsecId="";
    Drop4Request drop4Request;
    ArrayList sec;
    String[] listItemsecType;
    boolean[] checkedItemsecType;
    HashMap<Integer,Boolean> selsecType = new HashMap<>();




    @Override
    public void onBackPressed(){
        Intent a=new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        course_type =(TextView)findViewById(R.id.selected_cour);
        branch_type = (TextView)findViewById(R.id.selected_branch);
        relativeLayout1 = (RelativeLayout) findViewById(R.id.select_cour_type);
        relativeLayout2 = findViewById(R.id.select_branch_type);
        relativeLayout3 = findViewById(R.id.select_Sem_type);
        relativeLayout4 = findViewById(R.id.select_sec_type);
        onLoginSuccess();
        getbranch();
        getsem();
        getsec();


        mBuildercourType = new AlertDialog.Builder(dashboard.this, R.style.MyAlertDialogStyle);
        mBuildercourType.setTitle("Courses Types");
        mBuilderbranchType = new AlertDialog.Builder(dashboard.this, R.style.MyAlertDialogStyle);
        mBuilderbranchType.setTitle("Branch Types");
        progressDialog = new ProgressDialog(dashboard.this, R.style.MyAlertDialogStyle);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading ...");
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("dropdown", "clicked");
                openCourspinner();

            }
            void selectallcourtype() {
                sendcourId = "";
                selcourType.clear();
                Log.d("selectType", gson.toJson(coursetype));
                int i;
                for (i = 0; i < coursetype.size(); i++) {
                    selcourType.put(i, true);
                    sendcourId += coursetype.get(i).getSno() + ",";
                }
                course_type.setText(" ( All Selected ) ");

                checkedItemscourType = new boolean[coursetype.size()];
                listItemscourType = new String[coursetype.size()];
                int j;
                for (j = 0; j < coursetype.size(); j++) {
                    checkedItemscourType[j] = true;
                    listItemscourType[j] = coursetype.get(j).getValue();
                }
                sendcourId = removelast(sendcourId);
            }


             void openCourspinner() {
                progressDialog.show();
                mBuildercourType.setMultiChoiceItems(listItemscourType, checkedItemscourType, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if (isChecked) {
                            selcourType.put(position, true);
                        } else {
                            selcourType.put(position, false);
                        }

                    }
                });
                mBuildercourType.setCancelable(false);
                mBuildercourType.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String values = "";
                        int count = 0, i;
                        Log.e("01",gson.toJson(coursetype.size()));
                        for (i = 0; i < coursetype.size(); i++) {
                            if (selcourType.get(i)) {
                                values = values + coursetype.get(i).getSno() + "";
                                count++;
                            }
                        }
                        if (count >= coursetype.size()) {
                            selectallcourtype();
                        } else {
                            course_type.setText(" ( " + count + " Selected )");
                            sendcourId = removelast(values);
                            if (count == 0) {
                                Toast.makeText(dashboard.this,"no data",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                mBuildercourType.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        progressDialog.dismiss();
                        dialogInterface.dismiss();
                    }
                });
                 mDialogcourType = mBuildercourType.create();
                 mDialogcourType.show();


            }

        });

    }

    private void getsec() {

        drop4 drop4= ApiClient.createService(drop4.class);
        Call<Drop4Request> drop4RequestCall=drop4.dropresponse("get_sec","12,13","41,42","1,3");
        drop4RequestCall.enqueue(new Callback<Drop4Request>() {
            @Override
            public void onResponse(Call<Drop4Request> call, Response<Drop4Request> response) {
                drop4Request=response.body();
                sec=drop4Request.getSec();
                Log.d("drop4",gson.toJson(sec));
            }

            @Override
            public void onFailure(Call<Drop4Request> call, Throwable t) {
                Toast.makeText(dashboard.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getsem() {
        drop3 drop3 = ApiClient.createService(drop3.class);
        Call<Drop3Request> drop3RequestCall= drop3.dropresponse("get_sem","12,13","41,42");
        drop3RequestCall.enqueue(new Callback<Drop3Request>() {
            @Override
            public void onResponse(Call<Drop3Request> call, Response<Drop3Request> response) {
                drop3Request = response.body();
                sem = drop3Request.getSem();
                Gson gson = new Gson();
                String json= gson.toJson(sem);
                Log.d("drop3",json);
            }

            @Override
            public void onFailure(Call<Drop3Request> call, Throwable t) {
                Toast.makeText(dashboard.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }

    private void onLoginSuccess() {
        drop1 drop1 = ApiClient.createService(drop1.class);
        Call<Drop1Request> drop1RequestCall = drop1.dropresponse();
        drop1RequestCall.enqueue(new Callback<Drop1Request>() {
            @Override
            public void onResponse(Call<Drop1Request> call, Response<Drop1Request> response) {
                drop1Request = response.body();
                coursetype = drop1Request.getData();
                Gson gson = new Gson();
                String j1 = gson.toJson(coursetype);
                Log.d("j1",j1);
            }

            @Override
            public void onFailure(Call<Drop1Request> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(dashboard.this,t.getLocalizedMessage() ,Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void getbranch() {
        drop2 drop2 = ApiClient.createService(drop2.class);
        final Call<List<Drop2Request>> drop2RequestCall = drop2.dropresponse("get_branch","12");
        drop2RequestCall.enqueue(new Callback<List<Drop2Request>>() {
            @Override
            public void onResponse(Call<List<Drop2Request>> call, Response<List<Drop2Request>> response) {

                drop2Requests = response.body();
                Gson gson = new Gson();
                String j = gson.toJson(drop2Requests);
                Log.d("drop2",j);
            }

            @Override
            public void onFailure(Call<List<Drop2Request>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(dashboard.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }

        });

        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("drop2","clicked");
                progressDialog.show();
                openbranchspinner();

            }

            void openbranchspinner(){
                mBuilderbranchType.setMultiChoiceItems(listItemsbranchType, checkedItemsbranchType, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if (isChecked) {
                            selbranchType.put(position, true);

                        } else {
                            selbranchType.put(position, false);
                        }
                    }
                });
                mBuilderbranchType.setCancelable(false);
                mBuilderbranchType.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        String values="";
                        int count=0;
                        for (int i=0;i<drop2Requests.size();i++) {
                            if (selbranchType.get(i)) {
                                values = values + drop2Requests.get(i).getUid() + ",";
                                count++;
                            }
                        }
                        if (count>=drop2Requests.size()){
                            selectallbranchtype();
                        }else{
                            branch_type.setText(" ( "+count+"Selected )");
                            selbranchType.remove(values);
                            if (count==0){
                                Toast.makeText(dashboard.this,"No data",Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                });
                mBuilderbranchType.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });
                mDialoguebranchType = mBuilderbranchType.create();
                progressDialog.dismiss();
                mDialoguebranchType.show();
            }
        });

    }



    void selectallbranchtype() {
        sendbranchId = "";
        selbranchType.clear();
        int i;
        for (i = 0; i < drop2Requests.size(); i++) {
            selbranchType.put(i, true);
            sendbranchId += drop2Requests.get(i).getUid() + ",";
        }
        branch_type.setText(" ( All Selected ) ");

        checkedItemsbranchType = new boolean[drop2Requests.size()];
        listItemsbranchType = new String[drop2Requests.size()];
        int j;
        for (j = 0; j < drop2Requests.size(); j++) {
            checkedItemsbranchType[j] = true;
            listItemsbranchType[j] = drop2Requests.get(j).getDept_value();
        }
        sendbranchId = removelast(sendbranchId);
    }


    public String removelast(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
            str = str.substring(0, str.length() - 1);
        }
        return str;

    }

}
