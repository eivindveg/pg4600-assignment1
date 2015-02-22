package no.westerdals.student.vegeiv13.pg4600.assignment1.tictactoe.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Concept sourced from url http://androiddeveloperdemo.blogspot.no/2014/08/android-navigation-drawer-with-multiple.html
 */
public class BaseActivity extends Activity {

    protected String[] mListItems;
    protected DrawerLayout mDrawerLayout;
    protected ListView mDrawerList;
    protected FrameLayout mContentFrame;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        mTitle = getTitle();

        buildMenu();
        buildActionBar();
    }

    private void buildActionBar() {
        if (getActionBar() == null) {
            return;
        }

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mTitle);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


    private void buildMenu() {
        mContentFrame = (FrameLayout) findViewById(R.id.content_frame);
        mListItems = getResources().getStringArray(R.array.drawer_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, mListItems));
        mDrawerList.setOnItemClickListener((parent, view, position, id) -> selectItem(position));
    }

    private void selectItem(final int position) {
        mDrawerLayout.closeDrawer(mDrawerList);
        Class<? extends BaseActivity> intentClass;
        switch (position) {
            case 0:
                intentClass = NewGameActivity.class;
                break;
            case 1:
                Toast.makeText(this, "Not yet implemented", Toast.LENGTH_LONG).show();
                return;
            case 2:
                intentClass = LeaderboardActivity.class;
                break;
            default:
                return;
        }

        // Do not start an activity if it's the same
        if (intentClass.equals(this.getClass())) {
            return;
        }

        startActivity(new Intent(this, intentClass));
    }
}
