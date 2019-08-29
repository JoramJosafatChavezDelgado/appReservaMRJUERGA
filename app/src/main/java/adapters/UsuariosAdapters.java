package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.chavez.my_application.R;
import com.chavez.my_application.usuarios;

import java.util.ArrayList;

public class UsuariosAdapters extends ArrayAdapter<usuarios> {
        Context context;
        ArrayList<usuarios> items;

    private class ViewHolder {
        TextView id;
        Switch toogle;

        private ViewHolder() {
        }
    }



    public UsuariosAdapters(Context context, ArrayList<usuarios> _items) {
        super(context, 0, _items);
        this.items = _items;
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        UsuariosAdapters.ViewHolder holder;
        final usuarios rowItem = (usuarios) getItem(position);
        LayoutInflater mInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.registrodeusuarios, null);
            holder = new UsuariosAdapters.ViewHolder();

            holder.toogle = (Switch) convertView.findViewById(R.id.switch_on_off);
//            holder.more = (Button) convertView.findViewById(R.id.more);
            convertView.setTag(holder);
        } else {
            holder = (UsuariosAdapters.ViewHolder) convertView.getTag();
        }


        holder.toogle.setText(rowItem.getNombre());
        return convertView;
    }

    }
