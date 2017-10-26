package achan.nl.uitstelgedrag.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import achan.nl.uitstelgedrag.R;
import achan.nl.uitstelgedrag.domain.models.Label;

/**
 * Created by Etienne on 26-3-2016.
 */
public class LabelAdapter extends ArrayAdapter<Label> {

    List<Label> labels;

    public LabelAdapter(Context context, int resource, List<Label> labels) {
        super(context, resource);
        this.labels = labels;
    }

    @Override
    public void insert(Label object, int index) {
        labels.add(index, object);
        Log.i("LabelAdapter", "Item added: " + object.title);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        labels.clear();
        Log.i("LabelAdapter", "Cleared items.");
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (position > labels.size())
            return null;

        Label label = labels.get(position);

        View labelView = convertView != null?
                convertView :
                LayoutInflater.from(getContext()).inflate(R.layout.rowlayout_label, null);

        ImageView labelIcon = (ImageView) labelView.findViewById(R.id.label_icon);
        TextView labelTitle = (TextView) labelView.findViewById(R.id.label_title);
        TextView labelDescription = (TextView) labelView.findViewById(R.id.label_description);

        labelIcon.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_local_offer_black_24dp));
        labelTitle.setText(label.title);

        int defaultTextColor = getContext().getResources().getColor(R.color.accent);
        int textColor = label.color != null ? Integer.parseInt(label.color): defaultTextColor;
        labelTitle.setTextColor(textColor);

        labelDescription.setText("");

        if (label.location != null) {
            // fixme - Only for API 19! Deprecated since 22.
            labelIcon.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_place_black_24dp));

            StringBuilder locationFormatter = new StringBuilder("");

            if (label.location.city != null)
                locationFormatter.append(label.location.city);
            if (label.location.address != null)
                locationFormatter.append(", ").append(label.location.address);
            if (label.location.postalCode != null)
                locationFormatter.append(", ").append(label.location.postalCode);

            labelDescription.setText(locationFormatter);
            labelDescription.setTextColor(Color.WHITE);
        }

        return labelView;
    }

    @Override
    public int getCount() {
        return labels.size();
    }

    @Nullable
    @Override
    public Label getItem(int position) {
        return labels.get(position);
    }
}
