package com.example.feature.favorites.impl.presentation.viewmodel;

import com.example.core.database.dao.FavoriteCourseDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class FavoritesViewModel_Factory implements Factory<FavoritesViewModel> {
  private final Provider<FavoriteCourseDao> favoriteCourseDaoProvider;

  private FavoritesViewModel_Factory(Provider<FavoriteCourseDao> favoriteCourseDaoProvider) {
    this.favoriteCourseDaoProvider = favoriteCourseDaoProvider;
  }

  @Override
  public FavoritesViewModel get() {
    return newInstance(favoriteCourseDaoProvider.get());
  }

  public static FavoritesViewModel_Factory create(
      Provider<FavoriteCourseDao> favoriteCourseDaoProvider) {
    return new FavoritesViewModel_Factory(favoriteCourseDaoProvider);
  }

  public static FavoritesViewModel newInstance(FavoriteCourseDao favoriteCourseDao) {
    return new FavoritesViewModel(favoriteCourseDao);
  }
}
