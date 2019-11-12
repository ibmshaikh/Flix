package com.ibm.flix;

        import android.support.annotation.NonNull;
        import android.support.design.widget.BottomNavigationView;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        adapter = new PagerviewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

       /* Movies_Fregment movies_fregment = new Movies_Fregment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content,movies_fregment,movies_fregment.getTag()).commit();

        BottomNavigationView b=(BottomNavigationView)findViewById(R.id.bottom_navigation);

        b.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.mov:

                        Movies_Fregment movies_fregment = new Movies_Fregment();
                        FragmentManager manager = getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.content,movies_fregment,movies_fregment.getTag()).commit();


                        break;
                    case R.id.series:

                        Series_Fregment series_fregment = new Series_Fregment();
                        FragmentManager manager2 = getSupportFragmentManager();
                        manager2.beginTransaction().replace(R.id.content,series_fregment,series_fregment.getTag()).commit();

                        break;

                }

                return true;
            }
        });*/


    }


}
