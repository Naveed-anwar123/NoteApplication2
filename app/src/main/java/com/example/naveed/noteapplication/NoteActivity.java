package com.example.naveed.noteapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class NoteActivity  extends AppCompatActivity implements View.OnClickListener ,AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener,PopupMenu.OnMenuItemClickListener{


    private SharedPreferences sharedpre;
    private Toolbar note;
    TextView share;
    ImageButton noteLeft;
    private ArrayList<String> drawerLeft;
    ListView lvLeft;
    DrawerLayout draw;
    private ImageButton pop;
    private TabLayout tlMain;
    private ViewPager vpMain;
    private NoteTabs fspaMain;
    TextView  user;


    private ArrayAdapter<String> noteAdapter;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        sharedpre = getSharedPreferences("loginhandling",MODE_PRIVATE);
        share = (TextView)findViewById(R.id.share);
        user = (TextView)findViewById(R.id.user);
        user.setText(sharedpre.getString("email",""));
        share.setText(sharedpre.getString("email",""));
        note = (Toolbar) findViewById(R.id.notetb);
        setSupportActionBar(note);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(false);

        noteLeft = (ImageButton) note.findViewById(R.id.noteLeft);
        pop = (ImageButton) note.findViewById(R.id.pop);
        pop.setOnClickListener(this);;

        noteLeft.setOnClickListener(this);
        drawerLeft = new ArrayList<String>();
        drawerLeft.add("Add notes");
        drawerLeft.add("Log Out");

        noteAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drawerLeft);
        lvLeft = (ListView)findViewById(R.id.lvLeft);
        lvLeft.setAdapter(noteAdapter);
        draw = (DrawerLayout) findViewById(R.id.drawer);


        tlMain = (TabLayout) findViewById(R.id.tlMain);
        tlMain.addTab(tlMain.newTab().setText("All Notes").setIcon(R.drawable.a));
        tlMain.addTab(tlMain.newTab().setText("Add Note").setIcon(R.drawable.b));

        tlMain.setTabTextColors(getResources().getColor(android.R.color.darker_gray), getResources().getColor(android.R.color.white));

        tlMain.getTabAt(0).setIcon(R.drawable.a);

        tlMain.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpMain.setCurrentItem(tab.getPosition());

              /*  for (int i=0; i<tlMain.getTabCount(); i++) {
                    tlMain.getTabAt(i).setIcon(R.drawable.ic_launcher);
                }

                tlMain.getTabAt(tab.getPosition()).setIcon(R.drawable.ic_action_menu);
                */
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fspaMain = new NoteTabs(getSupportFragmentManager(), tlMain.getTabCount());
        vpMain = (ViewPager) findViewById(R.id.vpMain);
        vpMain.setAdapter(fspaMain);
        vpMain.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlMain));
        lvLeft.setOnItemClickListener(this);
    }

    @Override
        public void onClick(View v) {

        switch (v.getId()) {
            case R.id.noteLeft: {
                if (draw.isDrawerOpen(Gravity.LEFT)) {
                    draw.closeDrawer(Gravity.LEFT);
                } else {
                    draw.openDrawer(Gravity.LEFT);
                }
                break;
            }
            case R.id.pop:
            {
                PopupMenu popup = new PopupMenu(this, v);
                popup.setOnMenuItemClickListener(this);
                popup.inflate(R.menu.popup);
                popup.show();
                break;
            }
        }

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==1) {
                    SharedPreferences.Editor editor = sharedpre.edit();
                    editor.remove("email");
                    editor.commit();
                    Intent i = new Intent(this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.plog:
                SharedPreferences.Editor editor = sharedpre.edit();
                editor.remove("email");
                editor.commit();
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
                return true;
            default:
                return false;
        }

    }
}
