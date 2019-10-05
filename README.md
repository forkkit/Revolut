# Revolut Android Test
Here are the technical decisions made for the `Revolut Android Test`.

## Architecture

The Model-View-ViewModel (MVVM) design pattern was used in this app
because it allows for good separation of concerns and also because the
`ViewModel` does not hold a reference to the view. Android's `ViewModel`
is not destroyed when the view (in this case, the `Activity`) is
destroyed hence it allows for the view to be recreated with the existing
data in the `ViewModel`.

The Repository pattern was used to further achieve separation of
concerns since it decouples the business logic and the data access
layers. The network requests were made in the repository and the results
were sent back into the `ViewModel`.

The package structure in the app was set up by feature. This will make
it easy if a decision is made to split the app into multiple modules in
the future.

## Dependency Injection
Dagger 2 was used for Dependency Injection in this project. I took
advantage of the Dagger Android Libraries to allow for easy injection in
the view and `ViewModel` through the creation of modules and components
and wiring everything up in the Application class. A `ViewModelFactory`
was used to implement dynamic construction injection of dependencies
into the `ViewModel`.

## Networking
Retrofit was used to make network calls. A major reason for this was the
seamless integration with other libraries: 
-  `RxJava` - through a call adapter factory for supporting service
   method return types other than `Call`
- `Moshi` - through a converter factory for serialization and
  deserialization of `JSON`

A `sealed` class was used in the `ViewModel` to handle the different
states of the network request. This was used in conjunction with
`LiveData` to ensure the view was updated accordingly. This also allows
for easy testing of the `ViewModel` and the Repository.

## Data Configuration with Gradle
To ensure that the appropriate `base_url` will be used in the case
of staging and production environments, it was moved into the
`gradle.properties` file and retrieved
through the `BuildConfig` set up in the app level `build.gradle` file.
The `gradle.properties` is not usually checked into Version Control
Systems.

## Tests
This app was built with testability in mind.

The `ViewModel` and `Repository` was unit tested with `Mockito`. Dependencies have been injected where possible to be substituted with Mocks.

Although integration tests were not included, they can be done on the view with the `espresso` library.
Here, we will test that the correct views and values are displayed on
the screen. We can also provide mock API JSON data using a
`MockWebServer` to enable simulation of the various API response codes.

## UI
An abstraction was created for the image loading library used in the
view to ensure that it can be easily tested and for maintainability - a
decision to interchange it with other image loading libraries down the
line should not break the rest of the code.