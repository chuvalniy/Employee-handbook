# Package Structure


```
|-- employee-handbook
|   |-- core-common
|   |   |-- core
|   |   |   |-- ConnectivityObserver.kt
|   |   |   |-- Mapper
|   |   |   |-- NetworkConnectivityObserver
|   |   |   |-- Resource
|   |   |   |-- UiText
|   |   |-- helpers
|   |   |   |-- ViewBindingKotlinModel
|   |   |-- presentation
|   |   |   |-- BaseFragment.kt
|   |   |   |-- BaseViewModel.kt
|   |   |   |-- EventHandler
|   |   |   |-- UiEvent
|   |   |   |-- UiSideEffect
|   |   |   |-- UiState
|   |   |-- utils
|   |   |   |-- SortType.kt
|   |   |   |-- UtilsExtension.kt
|   |   |   |-- ViewExt 
|   |-- core-data
|   |   |-- mapper
|   |   |   |-- CacheMapper.kt
|   |   |   |-- NetworkMapper.kt
|   |   |-- repository
|   |   |   |-- DetailRepository.kt
|   |   |   |-- DetailRepositoryImpl.kt
|   |   |   |-- HomeRepository.kt
|   |   |   |-- HomeRepositoryImpl.kt
|   |-- core-database
|   |   |-- database
|   |   |   |-- CacheDao.kt
|   |   |   |-- CacheDatabase.kt
|   |   |   |-- CacheDataSource.kt
|   |   |-- model
|   |   |   |-- CacheModel.kt
|   |-- core-model
|   |   |-- model
|   |   |   |-- DomainUser.kt
|   |-- core-navigation
|   |   |-- navigation
|   |   |   |-- Fragment+Navigation.kt
|   |   |   |-- NavCommand.kt
|   |   |   |-- NavCommands.kt
|   |   |   |-- NavigationProvider.kt
|   |-- core-network
|   |   |-- model
|   |   |   |-- NetworkResponse.kt
|   |   |   |-- NetworkUser.kt
|   |   |-- network
|   |   |   |-- NetworkDataSource.kt
|   |   |   |-- NetworkRetrofit.kt
|   |-- core-preferences
|   |   |-- preferences
|   |   |   |-- UserPreferences.kt
|   |   |   |-- UserPreferencesImpl.kt
|   |-- feature-details
|   |   |-- di
|   |   |   |-- DetailComponent.kt
|   |   |   |-- DetailComponentViewModel.kt
|   |   |-- fragment
|   |   |   |-- DetailFragment.kt
|   |   |-- model
|   |   |   |-- DetailEvent.kt
|   |   |   |-- DetailSideEffect.kt
|   |   |   |-- DetailState.kt
|   |   |-- view_model
|   |   |   |-- DetailViewModel.kt
|   |   |   |-- DetailViewModelFactory.kt
|   |-- feature-home
|   |   |-- di
|   |   |   |-- HomeComponent.kt
|   |   |   |-- HomeComponentViewModel.kt
|   |   |-- fragment
|   |   |   |-- HomeFragment.kt
|   |   |-- model
|   |   |   |-- HomeEvent.kt
|   |   |   |-- HomeSideEffect.kt
|   |   |   |-- HomeState.kt
|   |   |-- view_model
|   |   |   |-- HomeViewModel.kt
|   |   |   |-- HomeViewModelFactory.kt
|   |-- feature-main
|   |   |-- fragment
|   |   |   |-- ErrorFragment
|   |   |   |-- MainFragment
```
