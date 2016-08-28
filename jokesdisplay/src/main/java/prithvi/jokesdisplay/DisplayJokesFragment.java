package prithvi.jokesdisplay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Prithvi on 8/3/2016.
 */
public class DisplayJokesFragment extends Fragment{

    private TextView textJoke;

    public DisplayJokesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_display_jokes, container, false);
        textJoke = (TextView) view.findViewById(R.id.tvJoke);

        textJoke.setText(getActivity().getIntent().getStringExtra("joke"));
        return view;
    }
}
