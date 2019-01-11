package shubhangi.toolbarexample.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import shubhangi.toolbarexample.R;
import shubhangi.toolbarexample.models.Movie;

public class MoviesAdapter extends ArrayAdapter<Movie> {
    private List<Movie> movies;

    private static class ViewHolderForNormalMovie {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        ImageView ivBackdrop;

        public ViewHolderForNormalMovie(View view) {
            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.tvOverview = (TextView) view.findViewById(R.id.tvOverview);
            this.ivPoster = (ImageView) view.findViewById(R.id.ivPoster);
            this.ivBackdrop = (ImageView) view.findViewById(R.id.ivBackdrop);
        }
    }

    private static class ViewHolderForPopularMovie {
        TextView tvTitle, tvReleaseDate, tvGenre, tvVoteAverage;
        ImageView ivBackdrop, ivVoteStart;

        public ViewHolderForPopularMovie(View view) {
            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.tvReleaseDate = (TextView) view.findViewById(R.id.tvReleaseDate);
            this.tvGenre = (TextView) view.findViewById(R.id.tvGenre);
            this.tvVoteAverage = (TextView) view.findViewById(R.id.tvVoteAverage);

            this.ivBackdrop = (ImageView) view.findViewById(R.id.ivBackdrop);
            this.ivVoteStart = (ImageView) view.findViewById(R.id.ivVoteStart);
        }
    }

    public MoviesAdapter(Context context, List<Movie> objects) {
        super(context, R.layout.normal_movie, objects);
        movies = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        int recycledViewType = getRecycledViewType(convertView);
        int viewTypeCount = getViewTypeCount();
        switch (itemViewType) {
            case 0:
                ViewHolderForNormalMovie viewHolderForNormalMovie;


                if (recycledViewType != itemViewType) {

                    convertView = getInflatedLayoutForType(itemViewType);

                    viewHolderForNormalMovie = new ViewHolderForNormalMovie(convertView);

                    convertView.setTag(viewHolderForNormalMovie);
                } else {

                    viewHolderForNormalMovie = (ViewHolderForNormalMovie) convertView.getTag();
                }

                bindViewHolderForNormalMovie(position, viewHolderForNormalMovie);

                return convertView;

            case 1:
                ViewHolderForPopularMovie viewHolderForPopularMovie;


                if (recycledViewType != itemViewType) {

                    convertView = getInflatedLayoutForType(itemViewType);

                    viewHolderForPopularMovie = new ViewHolderForPopularMovie(convertView);

                    convertView.setTag(viewHolderForPopularMovie);
                } else {

                    viewHolderForPopularMovie = (ViewHolderForPopularMovie) convertView.getTag();
                }

                bindViewHolderForPopularMovie(position, viewHolderForPopularMovie);

                return convertView;

            default:
                throw new IllegalArgumentException("itemViewType is wrong value");
        }
    }


    private void bindViewHolderForNormalMovie(int position, ViewHolderForNormalMovie viewHolder) {
        Movie movie = movies.get(position);

        if(viewHolder.tvTitle != null) {
            viewHolder.tvTitle.setText(movie.getTitle());
        }

        if(viewHolder.tvOverview != null) {
            viewHolder.tvOverview.setText(movie.getOverview());
        }

        int orientation = getContext().getResources().getConfiguration().orientation;

        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            loadImage(viewHolder.ivPoster, movie.getPosterPath());
        } else {
            loadImage(viewHolder.ivBackdrop, movie.getBackdropPath());
        }

    }

    public void bindViewHolderForPopularMovie(int position, ViewHolderForPopularMovie viewHolder) {
        Movie movie = movies.get(position);

        if(viewHolder.tvTitle != null) {
            viewHolder.tvTitle.setText(movie.getTitle());
        }

        if(viewHolder.tvVoteAverage != null) {
            viewHolder.tvVoteAverage.setText(Float.toString(movie.getVoteAverage()));
        }

        if(viewHolder.tvReleaseDate != null) {
            viewHolder.tvReleaseDate.setText(movie.getReleaseDate());
        }

        loadImage(viewHolder.ivBackdrop, movie.getBackdropPath());
    }

    private void loadImage(ImageView imageView, String path) {
        String imageUrl = "http://image.tmdb.org/t/p/w500" + path;

        if(imageView != null) {
            Picasso.with(getContext())
                    .load(imageUrl)
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.loading)
                    .into(imageView);


        }
    }


    @Override
    public int getViewTypeCount() {

        return 2;
    }


    @Override
    public int getItemViewType(int position) {

        Movie movie = getItem(position);
        if(movie.getVoteAverage() >= 5) {
            return 1;
        } else {
            return 0;
        }
    }

    public View getInflatedLayoutForType(int type) {
        switch (type) {
            case 0:
                return LayoutInflater.from(getContext()).inflate(R.layout.normal_movie, null);
            case 1:
                return LayoutInflater.from(getContext()).inflate(R.layout.popular_movie, null);
            default:
                return null;
        }
    }

    public int getRecycledViewType(View view) {
        if(view != null) {
            switch (view.getId()) {
                case R.id.normal_movie_in_list:
                    return 0;
                case R.id.popular_movie_in_list:
                    return 1;
                default:
                    return -1;
            }
        } else {
            return -1;
        }
    }
}
