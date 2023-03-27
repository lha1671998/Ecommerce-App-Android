package com.example.otomarket.fragment;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.otomarket.R;
import com.example.otomarket.activity.CartActivity;
import com.example.otomarket.activity.FavActivity;
import com.example.otomarket.activity.MainActivity;
import com.example.otomarket.adapter.Adapter_Xe;
import com.example.otomarket.model.Xe;
import com.example.otomarket.server.SERVER;
import com.example.otomarket.utils.PaginationScrollListener;
import com.example.otomarket.utils.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    View mViewList;
    Toolbar toolbarFL;
    AppCompatActivity appCompatActivity;

    SearchView searchView;
    SearchManager searchManager;

    RecyclerView rcXe;
    Adapter_Xe adapter_xe;
    ArrayList<Xe> dataXe = new ArrayList<>();

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    TextView txtFiler;
    EditText pMin, pMax;

    public static TextView bage, bageCart;
    ImageView imgFav, imgCart, imgClear;

    String giaMin, giaMax;

    public static FrameLayout frameLayoutFav, frameLayoutCart;

    VolleyRequest volleyRequest;

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

    ProgressBar progressBar;
    private int page = 1;
    private boolean isLoading = false;
    int visibleItemCount;
    int totalItemCount;
    int firstVisibleItemPosition;
    int preveousTotal;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewList = inflater.inflate(R.layout.fragment_list, container, false);

        AnhXa();

        setToolbarFL();
        LoadXe();
        rcXe.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (isLoading) {
                    if (totalItemCount > preveousTotal) {
                        preveousTotal = totalItemCount;
                        page += 1;
                        isLoading = false;
                    }
                }

                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    loadMoreItems();
                    isLoading = true;

//                    String TAG = "ListFragment";
//                    Log.v(TAG, "page number: " + page);
                }
            }
        });


        setSearchView();

        setSpinnerSort();

        setFiler();

        setResetList();

        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity(), CartActivity.class);
                startActivity(intent2);
            }
        });

        imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), FavActivity.class);
                startActivity(intent1);
            }
        });


        return mViewList;
    }

    public void AnhXa() {
        toolbarFL = mViewList.findViewById(R.id.toolbarListFrag);
        rcXe = mViewList.findViewById(R.id.rc_ListXeFrag);
        searchView = mViewList.findViewById(R.id.search_FragList);
        spinner = mViewList.findViewById(R.id.spinnerFrag);
        txtFiler = mViewList.findViewById(R.id.text_Filter);
        bage = mViewList.findViewById(R.id.badge_FL);
        bageCart = mViewList.findViewById(R.id.badge_Cart_FL);

        imgCart = mViewList.findViewById(R.id.imgCartFragList);

        imgFav = mViewList.findViewById(R.id.imgFavFragList);

        frameLayoutCart = mViewList.findViewById(R.id.frame_Cart_LF);
        frameLayoutFav = mViewList.findViewById(R.id.frame_Fav_LF);

        imgClear = mViewList.findViewById(R.id.rsList);
        progressBar = mViewList.findViewById(R.id.progressBar);

    }

    void setSpinnerSort() {
        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_array, R.layout.spinner_text);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void setResetList() {
        imgClear.setVisibility(View.INVISIBLE);
        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgClear.setVisibility(View.GONE);
                dataXe.clear();
                LoadXe();
            }
        });
    }

    void setFiler() {

        txtFiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDiaglog(Gravity.CENTER);
            }
        });
    }

    void showAlertDiaglog(int gravity) {
        final Dialog dialog = new Dialog(getActivity());
        //yêu cầu hộp thoại không có tiêu đề bằng cách yêu cầu cửa sổ của
        // hộp thoại không có tính năng Window.FEATURE_NO_TITLE.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_search);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Xác định vị trí Dialog
        //tạo một đối tượng WindowManager.LayoutParams để lưu trữ các thuộc tính của cửa sổ hiện tại.
        WindowManager.LayoutParams windowAttribute = window.getAttributes();
        //đặt thuộc tính vị trí của cửa sổ bằng biến gravity. Biến này được sử dụng để chỉ định
        // vị trí của cửa sổ trên màn hình, ví dụ như đặt cửa sổ ở giữa (Gravity.CENTER) hoặc bên trái (Gravity.LEFT).
        windowAttribute.gravity = gravity;
        //cập nhật các thuộc tính của cửa sổ hiện tại bằng cách sử dụng phương thức setAttributes()
        // của lớp Window. Sau khi cập nhật, cửa sổ sẽ được hiển thị trên màn hình với vị trí được thiết lập trước đó bằng biến gravity.
        window.setAttributes(windowAttribute);

        //Không tắt dialog khí nhấn ở ngoài rìa màn hình
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(false);
        }
        EditText edtMin = dialog.findViewById(R.id.priceMin);
        EditText edtMax = dialog.findViewById(R.id.priceMax);
        Button button = dialog.findViewById(R.id.ok);
        Button button1 = dialog.findViewById(R.id.cancle);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); // tắt dialog
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giaMin = edtMin.getText().toString();
                giaMax = edtMax.getText().toString();
                adapter_xe.clear();
                LoadXeFilter();
                imgClear.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public void setToolbarFL() {
        appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbarFL);
        appCompatActivity.getSupportActionBar().setTitle("Listings");
    }

    public void setSearchView() {
        searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        //getSearchableInfo() của đối tượng SearchManager để lấy thông tin tìm kiếm cho Activity hiện tại
        //hương thức này trả về một đối tượng SearchableInfo, chứa các thuộc tính của thanh tìm kiếm như icon, tiêu đề và tùy chọn khác.
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter_xe.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter_xe.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int idPos = position;
        switch (idPos) {
            case 0:
                sortAZ();
                break;
            case 1:
                sortZA();
                break;
            case 2:
                sortPriceAsc();
                break;
            case 3:
                sortPriceDesc();
                break;
        }
    }

    // Collections để sắp xếp danh sách dataXe các đối tượng xe.
    private void sortAZ() {
        Collections.sort(dataXe, new Comparator<Xe>() {
            @Override
            public int compare(Xe o1, Xe o2) {
                return o1.getTenXe().compareToIgnoreCase(o2.getTenXe());
            }
        });
        adapter_xe.notifyDataSetChanged();
    }


    private void sortZA() {
        Collections.sort(dataXe, new Comparator<Xe>() {
            @Override
            public int compare(Xe o1, Xe o2) {
                return o2.getTenXe().compareToIgnoreCase(o1.getTenXe());
            }
        });
        adapter_xe.notifyDataSetChanged();
    }

    private void sortPriceAsc() {
        Collections.sort(dataXe, new Comparator<Xe>() {
            @Override
            // sử dụng 1, -1 và 0 trong phương thức compare() là một cách chuẩn để định nghĩa
            // một bộ so sánh để sắp xếp các đối tượng trong danh sách.
            public int compare(Xe o1, Xe o2) {
                if (o1.getGiaXe() > o2.getGiaXe()) {
                    return 1;
                } else if (o1.getGiaXe() < o2.getGiaXe()) {
                    return -1;
                }
                return 0;
            }
        });
        adapter_xe.notifyDataSetChanged();
    }

    private void sortPriceDesc() {
        Collections.sort(dataXe, new Comparator<Xe>() {
            @Override
            public int compare(Xe o1, Xe o2) {
                if (o2.getGiaXe() > o1.getGiaXe()) {
                    return 1;
                } else if (o2.getGiaXe() < o1.getGiaXe()) {
                    return -1;
                }
                return 0;
            }
        });
        adapter_xe.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void LoadXeFilter() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        dataXe.add(new Xe(
                                jsonObject.getString("maxe"),
                                jsonObject.getString("tenxe"),
                                jsonObject.getString("anhxe"),
                                jsonObject.getString("mahang"),
                                jsonObject.getString("mapk"),
                                jsonObject.getInt("dongia"),
                                jsonObject.getInt("soluongxe")));
                    } catch (JSONException e) {
                        Toast.makeText(getContext(), "loi", Toast.LENGTH_SHORT).show();

                    }
                }
                adapter_xe.notifyDataSetChanged();
            }
        };

        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "That Bai", Toast.LENGTH_SHORT).show();
            }
        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.filterPath + "pricemin=" + giaMin + "&pricemax=" + giaMax, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

        adapter_xe = new Adapter_Xe(getContext(), dataXe);
        rcXe.setAdapter(adapter_xe);
        rcXe.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    public void LoadXe() {
        progressBar.setVisibility(View.VISIBLE);
        String url = SERVER.xePath + page;
        Toast.makeText(getActivity(), url, Toast.LENGTH_SHORT).show();
        volleyRequest = new VolleyRequest(getContext());
        volleyRequest.makeGetRequest(url, Request.Method.GET, null, new VolleyRequest.VolleyResponseListener() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        dataXe.add(new Xe(
                                jsonObject.getString("maxe"),
                                jsonObject.getString("tenxe"),
                                jsonObject.getString("anhxe"),
                                jsonObject.getString("mahang"),
                                jsonObject.getString("mapk"),
                                jsonObject.getInt("dongia"),
                                jsonObject.getInt("soluongxe")));
                    } catch (JSONException e) {
                        Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
                adapter_xe.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getContext(), "That Bai", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });
        adapter_xe = new Adapter_Xe(getContext(), dataXe);
        rcXe.setAdapter(adapter_xe);
        rcXe.setLayoutManager(linearLayoutManager);
    }

    private void loadMoreItems() {
        LoadXe();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.xeFavArrayList != null) {
            bage.setText(String.valueOf(MainActivity.xeFavArrayList.size()));
        }
        if (MainActivity.manggiohang != null) {
            long tongSoLuong = 0;
            for (int i = 0; i < MainActivity.manggiohang.size(); i++) {
                tongSoLuong = tongSoLuong + MainActivity.manggiohang.get(i).getSoluongxe();
            }
            bageCart.setText(String.valueOf(tongSoLuong));
        }
    }
}