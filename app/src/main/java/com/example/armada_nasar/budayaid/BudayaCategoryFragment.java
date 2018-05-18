package com.example.armada_nasar.budayaid;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BudayaCategoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BudayaCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BudayaCategoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String getBudayaByProvinceIdAndCategoryIdEndpoint = "http://35.194.234.226:6014/getJSONBudayasByProvinceIdAndCategory/";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BudayaCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BudayaCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BudayaCategoryFragment newInstance(String param1, String param2) {
        BudayaCategoryFragment fragment = new BudayaCategoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static BudayaCategoryFragment newInstance(int provinceId, int categoryID) {
        BudayaCategoryFragment fragment = new BudayaCategoryFragment();
        Bundle args = new Bundle();
        args.putInt("provinceId", provinceId);
        args.putInt("categoryId", categoryID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private ArrayList<Budaya> alb = new ArrayList<>();
    private RecyclerView budayaList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_budaya_category, container, false);

        Bundle params = getArguments();
        BudayaJSONDownloader bd;

        if (params != null) {
            int provinceId = params.getInt("provinceId");
            int categoryId = params.getInt("categoryId");

            bd = new BudayaJSONDownloader(getBudayaByProvinceIdAndCategoryIdEndpoint + provinceId + "/" + categoryId);
            Log.d("categoryurl", getBudayaByProvinceIdAndCategoryIdEndpoint+ provinceId + "/" + categoryId);
        }
        else {
            bd = new BudayaJSONDownloader("http://35.194.234.226:6014/getJSONBudayasByProvinceId/1");
        }

        bd.execute();

        budayaList = (RecyclerView) v.findViewById(R.id.budayaList);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void updateUi() {
        budayaList.setLayoutManager(new LinearLayoutManager(getContext()));
        budayaList.setAdapter(new CardviewAdapter(getContext(), alb));
    }
    private class BudayaJSONDownloader extends AsyncTask<URL, Void, ArrayList<Budaya>> {

        private String downloadURL = "";
        private ArrayList<Budaya> budayaList = new ArrayList<Budaya>();

        public BudayaJSONDownloader(String url) {
            super();
            this.downloadURL = url;
        }

        @Override
        protected ArrayList<Budaya> doInBackground(URL... urls) {
            // Create URL object
            URL url = createUrl(downloadURL);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
            ArrayList<Budaya> budayas = extractFeatureFromJson(jsonResponse);

            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
            return budayas;
        }

        /**
         * Update the screen with the given earthquake (which was the result of the
         * {@link com.example.armada_nasar.budayaid.BudayaDownloader}).
         */
        @Override
        protected void onPostExecute(ArrayList<Budaya> budayas) {
            if (budayas == null) {
                return;
            }

            alb = budayas;

            updateUi();
        }

        /**
         * Returns new URL object from the given string URL.
         */
        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Log.e("url process", "Error with creating URL", exception);
                return null;
            }
            return url;
        }

        /**
         * Make an HTTP request to the given URL and return a String as the response.
         */
        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } catch (IOException e) {
                // TODO: Handle the exception
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        /**
         * Convert the {@link InputStream} into a String which contains the
         * whole JSON response from the server.
         */
        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        /**
         * Return an {@link ArrayList<Budaya>} object by parsing out information
         * about the first earthquake from the input responseJSON string.
         */
        private ArrayList<Budaya> extractFeatureFromJson(String responseJSON) {
            try {
                JSONObject baseJsonResponse = new JSONObject(responseJSON);
                JSONArray budayaArray = baseJsonResponse.getJSONArray("budaya");

                for (int i = 0; i < budayaArray.length(); i++) {
                    JSONObject budayaItem = budayaArray.getJSONObject(i);

                    budayaList.add(new Budaya(budayaItem.getString("name"), "", budayaItem.getString("google_search_term"), budayaItem.getString("image_url")));
                }
                return budayaList;
            } catch (JSONException e) {
                Log.e("JSON Problem", "Problem parsing the earthquake JSON results", e);
            }
            return null;
        }

        public ArrayList<Budaya> getBudayasList() {
            return budayaList;
        }
    }
}
