package tv.emby.embyatv.browsing;

import mediabrowser.apiinteraction.Response;
import mediabrowser.model.dto.BaseItemDto;
import mediabrowser.model.querying.ItemSortBy;
import mediabrowser.model.querying.ItemsByNameQuery;
import mediabrowser.model.querying.ItemsResult;
import tv.emby.embyatv.TvApp;
import tv.emby.embyatv.querying.StdItemQuery;
import tv.emby.embyatv.util.DelayedMessage;

/**
 * Created by Eric on 12/4/2014.
 */
public class ByGenreFragment extends CustomViewFragment {

    @Override
    protected void setupQueries(final IRowLoader rowLoader) {

        if (mFolder.getChildCount() > 0) {
            final DelayedMessage message = new DelayedMessage(getActivity());

            //Get all genres for this folder
            ItemsByNameQuery genres = new ItemsByNameQuery();
            genres.setParentId(mFolder.getId());
            genres.setSortBy(new String[]{ItemSortBy.SortName});
            if (includeType != null) genres.setIncludeItemTypes(new String[]{includeType});
            genres.setRecursive(true);
            genres.setUserId(TvApp.getApplication().getCurrentUser().getId());
            TvApp.getApplication().getApiClient().GetGenresAsync(genres, new Response<ItemsResult>() {
                @Override
                public void onResponse(ItemsResult response) {
                    for (BaseItemDto genre : response.getItems()) {
                        StdItemQuery genreQuery = new StdItemQuery();
                        genreQuery.setParentId(mFolder.getId());
                        genreQuery.setSortBy(new String[]{ItemSortBy.SortName});
                        if (includeType != null) genreQuery.setIncludeItemTypes(new String[]{includeType});
                        genreQuery.setGenres(new String[] {genre.getName()});
                        genreQuery.setRecursive(true);
                        mRows.add(new BrowseRowDef(genre.getName(), genreQuery, 40));
                    }

                    if (mRows.size() < 2) setHeadersState(HEADERS_DISABLED);

                    rowLoader.loadRows(mRows);

                    message.Cancel();
                }
            });
        }
        else {
            setHeadersState(HEADERS_DISABLED);
        }
    }


}
