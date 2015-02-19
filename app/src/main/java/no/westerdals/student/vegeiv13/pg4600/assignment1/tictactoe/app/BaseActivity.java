package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class BaseActivity extends Activity {

    protected String[] mListItems;
    protected DrawerLayout mDrawerLayout;

    protected ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mListItems = getResources().getStringArray(R.array.drawer_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, mListItems));
        mDrawerList.setOnItemClickListener((parent, view, position, id) -> selectItem(position));

    }

    private void selectItem(final int position) {
        mDrawerLayout.closeDrawer(mDrawerList);
        Class<? extends BaseActivity> intentClass;
        switch(position) {
            case 0: intentClass = NewGameActivity.class;
                break;
            default: return;
        }

        // Do not start an activity if it's the same
        if(intentClass.equals(this.getClass())) {
            return;
        }

        startActivity(new Intent(this, intentClass));
    }
}
