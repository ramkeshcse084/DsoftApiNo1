package com.nestedmango.dsoftapino1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

public class CityAdapter extends ArrayAdapter<CityPlace> {
	ArrayList<CityPlace> cityList;
	LayoutInflater vi;
	int Resource;
	ViewHolder holder;

	public CityAdapter(Context context, int resource, ArrayList<CityPlace> objects) {
		super(context, resource, objects);
		vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		cityList = objects;
	}
 
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// convert view = design
		View v = convertView;
		if (v == null) {
			holder = new ViewHolder();
			v = vi.inflate(Resource, null);
			holder.imageview = (ImageView) v.findViewById(R.id.ivImage);
			holder.tvName = (TextView) v.findViewById(R.id.tvName);

			holder.tvStatus = (TextView) v.findViewById(R.id.tvStatus);
			holder.tvId= (TextView) v.findViewById(R.id.tvId);
			holder.tvCreateDone = (TextView) v.findViewById(R.id.tvCreateDone);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.imageview.setImageResource(R.drawable.ram);
		new DownloadImageTask(holder.imageview).execute(cityList.get(position).getImage());
		holder.tvName.setText(cityList.get(position).getName());
		holder.tvCreateDone.setText(cityList.get(position).getCretedone());
		holder.tvId.setText(cityList.get(position).getId());
		holder.tvStatus.setText(cityList.get(position).getStatus());

		return v;

	}

	static class ViewHolder {
		public ImageView imageview;
		public TextView tvName;

		public TextView tvStatus;
		public TextView tvCreateDone;
		public TextView tvId;


	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}

	}
}