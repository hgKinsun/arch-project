# Project guidelines
----

## Prefer Maven dependency resolution instead of importing jar files
If you explicitly include jar files in your project, they will be of some specific frozen version, such as `2.1.1`. Downloading jars and handling updates is cumbersome, this is a problem that Maven solves properly, and is also encouraged in Android Gradle builds. For example:

```groovy
dependencies {
    compile 'com.squareup.okhttp:okhttp:2.2.0'
    compile 'com.squareup.okhttp:okhttp-urlconnection:2.2.0'
}
```    

## Avoid Maven dynamic dependency resolution
Avoid the use of dynamically versioned, such as `2.1.+` as this may result in different and unstable builds or subtle, untracked differences in behavior between builds. The use of static versions such as `2.1.1` helps create a more stable, predictable and repeatable development environment.

## Activities and Fragments

Because of Android API's history, you can loosely consider Fragments as UI pieces of a screen. In other words, Fragments are normally related to UI. Activities can be loosely considered to be controllers, they are specially important for their lifecycle and for managing state. However, you are likely to see variation in these roles: activities might be take UI roles ([delivering transitions between screens](https://developer.android.com/about/versions/lollipop.html)), and [fragments might be used solely as controllers](http://developer.android.com/guide/components/fragments.html#AddingWithoutUI).

- Avoid using [nested fragments](https://developer.android.com/about/versions/android-4.2.html#NestedFragments) extensively, because [matryoshka bugs](http://delyan.me/android-s-matryoshka-problem/) can occur. Use nested fragments only when it makes sense (for instance, fragments in a horizontally-sliding ViewPager inside a screen-like fragment) or if it's a well-informed decision.
- Avoid putting too much code in activities. Whenever possible, keep them as lightweight containers, existing in your application primarily for the lifecycle and other important Android-interfacing APIs. Prefer single-fragment activities instead of plain activities - put UI code into the activity's fragment. This makes it reusable in case you need to change it to reside in a tabbed layout, or in a multi-fragment tablet screen. Avoid having an activity without a corresponding fragment, unless you are making an informed decision.
- Don't abuse Android-level APIs such as heavily relying on Intent for your app's internal workings. You could affect the Android OS or other applications, creating bugs or lag. For instance, it is known that if your app uses Intents for internal communication between your packages, you might incur multi-second lag on user experience if the app was opened just after OS boot.

## Class files

Class names are written in [UpperCamelCase](http://en.wikipedia.org/wiki/CamelCase). 

For classes that extend an Android component, the name of the class should end with the name of the component; for example: `SignInActivity`, `SignInFragment`, `ImageUploaderService`, `ChangePasswordDialog`.

## Resources files
Resources file names are written in __lowercase_underscore__. 

## Drawable files

Use 2-3 characters prefix for drawables.
Naming conventions for drawables:

| Asset Type   | Prefix            |        Example              |
|--------------| ------------------|-----------------------------|
| Action bar   | `ab_`             | `ab_stacked.9.png`          |
| Background   | `bg_`             | `bg_button_blue.xml`      |
| Button       | `btn_`            | `btn_send_pressed.9.png`    |
| Dialog       | `dlg_`            | `dlg_top.9.png`             | 
| Divider      | `div_`            | `div_horizontal.9.png`      |
| Icon         | `ic_`             | `ic_star.png`               |
| Menu         | `mn_ `            | `mn_submenu_bg.9.png`       |
| Notification | `ntf_`            | `ntf_bg.9.png`              |
| Selector     | `selector_`       | `selector_bg.xml`         |
| Tabs         | `tab_`            | `tab_pressed.9.png`         |

Naming conventions for icons (taken from [Android iconography guidelines](http://developer.android.com/design/style/iconography.html)):

| Asset Type                      | Prefix             | Example                      |
| --------------------------------| ----------------   | ---------------------------- | 
| Icons                           | `ic_`              | `ic_star.png`                |
| Launcher icons                  | `ic_launcher`      | `ic_launcher_calendar.png`   |
| Menu icons and Action Bar icons | `ic_menu`          | `ic_menu_archive.png`        |
| Status bar icons                | `ic_stat_notify`   | `ic_stat_notify_msg.png`     |
| Tab icons                       | `ic_tab`           | `ic_tab_recent.png`          |
| Dialog icons                    | `ic_dialog`        | `ic_dialog_info.png`         |

Naming conventions for selector states:

| State        | Suffix          | Example                     |
|--------------|-----------------|-----------------------------|
| Normal       | `_normal`       | `btn_order_normal.9.png`    |
| Pressed      | `_pressed`      | `btn_order_pressed.9.png`   |
| Focused      | `_focused`      | `btn_order_focused.9.png`   |
| Disabled     | `_disabled`     | `btn_order_disabled.9.png`  |
| Selected     | `_selected`     | `btn_order_selected.9.png`  |


## Layout files
Layout files should match the name of the Android components that they are intended for but moving the top level component name to the beginning. For example, if we are creating a layout for the `SignInActivity`, the name of the layout file should be `activity_sign_in.xml`.

| Component        | Class Name             | Layout Name                   |
| ---------------- | ---------------------- | ----------------------------- |
| Activity         | `UserProfileActivity`  | `activity_user_profile.xml`   |
| Fragment         | `SignUpFragment`       | `fragment_sign_up.xml`        |
| Dialog           | `ChangePasswordDialog` | `dialog_change_password.xml`  |
| AdapterView item | ---                    | `item_person.xml`             |
| Partial layout   | ---                    | `partial_stats_bar.xml`       |

A slightly different case is when we are creating a layout that is going to be inflated by an `Adapter`, e.g to populate a `ListView`. In this case, the name of the layout should start with `item_`

Note that there are cases where these rules will not be possible to apply. For example, when creating layout files that are intended to be part of other layouts. In this case you should use the prefix `partial_`

## Menu files  

Similar to layout files, menu files should match the name of the component. For example, if we are defining a menu file that is going to be use in the `UserActivity`, then the name of the file should be `activity_user.xml`

A good practise is to not include the word `menu` as part of the name because these files are already located in directory called menu. 

## Values files

Resource files in the values folder should be __plural__, e.g. `strings.xml`, `styles.xml`, `colors.xml`, `dimens.xml`, `attrs.xml`

## Language

Use __English__ for everything (packages, classes, interfaces, variables, methods, comments, javadocs, yes.. everything)

# Code guidelines 
----

## Don't ignore exceptions

You must never do the following:

```java
void setServerPort(String value) {
    try {
        serverPort = Integer.parseInt(value);
    } catch (NumberFormatException e) { }
}
```

_While you may think that your code will never encounter this error condition or that it is not important to handle it, ignoring exceptions like above creates mines in your code for someone else to trip over some day. You must handle every Exception in your code in some principled way. The specific handling varies depending on the case._ - ([Android code style guidelines](https://source.android.com/source/code-style.html))

See alternatives [here](https://source.android.com/source/code-style.html#dont-ignore-exceptions).
    
## Don't catch generic exception

You should not do this:

```java
try {
    someComplicatedIOFunction();        // may throw IOException 
    someComplicatedParsingFunction();   // may throw ParsingException 
    someComplicatedSecurityFunction();  // may throw SecurityException 
    // phew, made it all the way 
} catch (Exception e) {                 // I'll just catch all exceptions 
    handleError();                      // with one generic handler!
}
```

See the reason why and some alternatives [here](https://source.android.com/source/code-style.html#dont-catch-generic-exception)

## Don't use finalizers

_We don't use finalizers. There are no guarantees as to when a finalizer will be called, or even that it will be called at all. In most cases, you can do what you need from a finalizer with good exception handling. If you absolutely need it, define a `close()` method (or the like) and document exactly when that method needs to be called. See `InputStream` for an example. In this case it is appropriate but not required to print a short log message from the finalizer, as long as it is not expected to flood the logs._ - ([Android code style guidelines](https://source.android.com/source/code-style.html#dont-use-finalizers))

## Fully qualify imports

This is bad: `import foo.*;`

This is good: `import foo.Bar;`

See more info [here](https://source.android.com/source/code-style.html#fully-qualify-imports)

# Java style rules
----

## Fields definition and naming 

Fields should be defined at the __top of the file__ and they should follow the naming rules listed below.

* Private, non-static field names start with __m__.
* Private, static field names start with __s__.
* Other fields start with a lower case letter.
* Static final fields (constants) are ALL_CAPS_WITH_UNDERSCORES.

Example:

```java
public class MyClass {
    public static final int SOME_CONSTANT = 42;
    public int publicField;
    private static MyClass sSingleton;
    int mPackagePrivate;
    protected int mProtected;
    private int mPrivate;
}
```
    
## Treat acronyms as words

| Good           | Bad            |
| -------------- | -------------- |
| `XmlHttpRequest` | `XMLHTTPRequest` | 
| `getCustomerId`  | `getCustomerID`  | 
| `String url`     | `String URL`     |
| `long id`        | `long ID`        |

## Blocks indentation

Blocks that are nested more should be indented more.
Blocks that are nested the same should be indented the same.

```java
blah;
blah;
for (...) {
    blah;
    blah;
    for (...) {
        blah;
        blah;
    }
}
```

This is __bad__:
```java
blah;
blah;
for (...) {
blah;
blah;
for (...) {
blah;
blah;
}
}
```

This is __bad__ too:

```java
blah;
 blah;
for(...) {
    blah;
     blah;
    for(...) {
        blah;
         blah;
    }
    }
```

## Open braces

Put __{__ with previous line, not on its own line

```java
public void foo() {
    if (...) {
        doSomething();
    }
}
```

This is __bad__ 

```java
public void foo()
{
    if (...)
    {
        doSomething();
    }
}
```

## Use spaces for indentation

Use __2 space__ indents for blocks:

```java
if (x == 1) {
  x++;
}
```

Use __4 space__ indents for line wraps:

```java
Instrument i =
    someLongExpression(that, wouldNotFit, on, one, line);
```

## Use standard brace style

Braces go on the same line as the code before them.

```java
class MyClass {
    int func() {
        if (something) {
            // ...
        } else if (somethingElse) {
            // ...
        } else {
            // ...
        }
    }
}
```

Braces around the statements are required unless the condition and the body fit on one line. 

If the condition and the body fit on one line and that line is shorter than the max line length, then do __not__ use braces e.g.

```java
if (condition) body();
```

This is __bad__:

```java
if (condition)
    body();  // bad!
```
        
## Use standard Java annotations

According to the Android code style guide, the standard practices for some of the predefined annotations in Java are:

* `@Override`: The @Override annotation __must be used__ whenever a method overrides the declaration or implementation from a super-class. For example, if you use the @inheritdocs Javadoc tag, and derive from a class (not an interface), you must also annotate that the method @Overrides the parent class's method.

* `@SuppressWarnings`: The @SuppressWarnings annotation should only be used under circumstances where it is impossible to eliminate a warning. If a warning passes this "impossible to eliminate" test, the @SuppressWarnings annotation must be used, so as to ensure that all warnings reflect actual problems in the code.

More information about annotations guidelines can be found [here](http://source.android.com/source/code-style.html#use-standard-java-annotations).


## Limit variable scope 

_The scope of local variables should be kept to a minimum (Effective Java Item 29). By doing so, you increase the readability and maintainability of your code and reduce the likelihood of error. Each variable should be declared in the innermost block that encloses all uses of the variable._ 

_Local variables should be declared at the point they are first used. Nearly every local variable declaration should contain an initializer. If you don't yet have enough information to initialize a variable sensibly, you should postpone the declaration until you do._ - ([Android code style guidelines](https://source.android.com/source/code-style.html#limit-variable-scope))

## Initialize local variables when declared  

Do
```java
String s = "Hello";
```

This is __bad__

```java
String s;
...
s = "Hello";
```

Except try/catch blocks

```java
int n;
try {
    n = Integer.parseInt(someString);
} catch(NumberFormatException nfe) {
    n = 10;
}
```

## Order import statements 

If you are using an IDE such as Android Studio, you don't have to worry about this because your IDE is already obeying these rules. If not, have a look below.

The ordering of import statements is:

1. Android imports
2. Imports from third parties (com, junit, net, org)
3. java and javax
4. Same project imports 

To exactly match the IDE settings, the imports should be:

* Alphabetical within each grouping, with capital letters before lower case letters (e.g. Z before a).
* There should be a blank line between each major grouping (android, com, junit, net, org, java, javax).

More info [here](https://source.android.com/source/code-style.html#limit-variable-scope)
    
## Class member ordering 

There is no single correct solution for this but using a __logical__ and __consistent__ order will improve code learnability and readability. It is recommendable to use the following order:

1. Constants 
2. Fields 
3. Constructors 
4. Override methods and callbacks (public or private)
5. Public methods
6. Private methods
7. Inner classes or interfaces

Example:

```java
public class MainActivity extends Activity {

    private String mTitle;
    private TextView mTextViewTitle;

    @Override 
    public void onCreate() {
        ...
    }
    
    public void setTitle(String title) {
        mTitle = title;
    }
    
    private void setUpView() {
        ...
    }
    
    static class AnInnerClass {
    
    }

} 
```

If your class is extending and __Android component__ such as an Activity or a Fragment, it is a good practise to order the override methods so that they __match the component's lifecycle__. For example, if you have an Activity that implements `onCreate()`, `onDestroy()`, `onPause()` and `onResume()`, then the correct order is:

```java
public class MainActivity extends Activity {

    //Order matches Activity lifecycle  
    @Override 
    public void onCreate() {} 
    
    @Override 
    public void onResume() {}
    
    @Override 
    public void onPause() {}
    
    @Override 
    public void onDestory() {}

}
```

## Write short methods

No official limit, but try to keep methods short and focused. Think often about how to refactor your code to break it into smaller and more reusable pieces.

## Parameter ordering in methods

When programming for Android, it is quite common to define methods that take a `Context`. If you are writing a method like this, then the __Context__ must be the __first__ parameter.

The opposite case are __callback__ interfaces that should always be the __last__ parameter.

Examples:

```java
// Context always go first
public User loadUser(Context context, int userId);

// Callbacks always go last
public void loadUserAsync(Context context, int userId, UserCallback callback);
```

## String constants, naming and values

Many elements of the Android SDK such as `SharedPreferences`, `Bundle` or `Intent` use a key-value pair approach so it's very likely that even for a small app you end up having to write a lot of String constants.

When using one of these components, you __must__ define the keys as a `static final` fields and they should be prefixed as indicaded below. 

| Element            | Field Name Prefix |
| -----------------  | ----------------- |
| SharedPreferences  | `PREF_`             |
| Bundle             | `BUNDLE_`           | 
| Fragment Arguments | `ARGUMENT_`         |   
| Intent Extra       | `EXTRA_`            |
| Intent Action      | `ACTION_`           |

Note that the arguments of a Fragment - `Fragment.getArguments()` - are also a Bundle. However, because this is a quite common use of Bundles, we define a different prefix for them. 

Example:

```java
// Note the value of the field is the same as the name to avoid duplication issues
static final String PREF_EMAIL = "PREF_EMAIL";
static final String BUNDLE_AGE = "BUNDLE_AGE";
static final String ARGUMENT_USER_ID = "ARGUMENT_USER_ID";

// Intent-related items use full package name as value
static final String EXTRA_SURNAME = "com.myapp.extras.EXTRA_SURNAME";
static final String ACTION_OPEN_USER = "com.myapp.action.ACTION_OPEN_USER";
```

## Arguments in Fragments and Activities

When data is passed into an `Activity `or `Fragment` via `Intents` or a `Bundles`, the keys for the different values __must__ follow the rules described in the section above.

When an `Activity` or `Fragment` expect arguments, it should provide a `static public` method that facilitates the creation of the `Fragment` or `Intent`.

In the case of Activities the method is usually called `getStartIntent()`

```java
public static Intent getStartIntent(Context context, User user) {
    Intent intent = new Intent(context, ThisActivity.class);
    intent.putParcelableExtra(EXTRA_USER, user);
    return intent;
}
```

For Fragments it's named `newInstance()` and it handles the creation of the Fragment with the right arguments. 

```java
public static UserFragment newInstance(User user) {
    UserFragment fragment = new UserFragment;
    Bundle args = new Bundle();
    args.putParcelable(ARGUMENT_USER, user);
    fragment.setArguments(args)
    return fragment;
}
```

__Note 1__: these methods should go at the top of the class before `onCreate()`

__Note 2__: if we provide the methods described above, the keys for extras and arguments should be `private` because there is not need for them to be exposed outside the class. 

## Line length limit

Code lines should not exceed __100 characters__. If the line is longer than this limit there are usually two options to reduce its length:

* Extract a local variable or method (Preferable).
* Apply line-wrapping to divide a single line into multiple ones. 

There are two __exceptions__ where is possible to have lines longer than 100:

* Lines that are not possible to split, e.g. long URLs in comments.
* `package` and `import` statements. 

## Line-wrapping strategies

There isn't an exact formula that explains how to line-wrap and quite often different solutions are valid. However there are a few rules that can be applied to common cases.

__Method chain case__ 

When multiple methods are chained in the same line - for example when using Builders - every call to a method should go in its own line, breaking the line before the `.`

```java
Picasso.with(context).load("http://sweetclipart.com/multisite/sweetclipart/files/letter_a.png").into(imageView);
```

To
```java
Picasso.with(context)
        .load("http://sweetclipart.com/multisite/sweetclipart/files/letter_a.png")
        .into(imageView);
```

__Long parameters case__

When a method has many parameters or its parameters are very long we should break the line after every comma `,`

```java
loadPicture(context, "http://sweetclipart.com/multisite/sweetclipart/files/letter_a.png", mImageViewProfilePicture, clickListener, "Title of the picture");
```

To
```java
loadPicture(context,
        "http://sweetclipart.com/multisite/sweetclipart/files/letter_a.png",
        mImageViewProfilePicture,
        clickListener,
        "Title of the picture");
```

## RxJava chains styling 

Rx chains of operators require line-wrapping. Every operator must go in a new line and the line should be broken before the `.`

```java
public Observable<Location> syncLocations() {
    return mDatabaseHelper.getAllLocations()
            .concatMap(new Func1<Location, Observable<? extends Location>>() {
                @Override
                 public Observable<? extends Location> call(Location location) {
                     return mConcurService.getLocation(location.id);
                 }
            })
            .retry(new Func2<Integer, Throwable, Boolean>() {
                 @Override
                 public Boolean call(Integer numRetries, Throwable throwable) {
                     return throwable instanceof RetrofitError;
                 }
            });
}
```

## Use JavaDoc

Use JavaDoc from __the beginning__

Don’t wait until the code is finished. Short comments are fine, but use some. Explain purpose and non-obvious behavior. Don’t explain standard Java constructs.

Document every __class__

```java
/** Represents a collection of Blahs. Used to … **/
public class Foo { … }
```

Document anything __public__

* Methods
* Constructors
* Instance variables

Start JavaDoc comments with __3rd person verb__

* Represents a ...
* Responds to mouse clicks with ...
* Deletes ...

Don't

* This class ...
* This method ...

__Review [Oracle JavaDoc](http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html) guidelines__
    
## Use TODO Comments for Temporary Code

Use __"// TODO: ..."__ for code that needs to be changed later because some situations :

* Temporary fix
* OK but not great
* Works for small sizes, but bad performance in future when data sets get bigger.

For example

```java
// TODO: Switch to a Map when you have more entries
// TODO: Remove after UrlTable2 has been checked in
```
    
## String literal concatenations

when building html structure from string and method result, structure the code like the html result, make sure to preserve indentation

*Don't*
```
+ "<div class='ntap'>"
    + "<i class='like'></i>"
    + "<span class='offset-rating'>" + formatNumber(thread.getVoteNum())
    + getString(R.string.thread_detail_ntap_label) + "</span>"
+ "</div>"
```

*Do*
```
+ "<div class='ntap'>"
    + "<i class='like'></i>"
    + "<span class='offset-rating'>"
        + formatNumber(thread.getVoteNum())
        + getString(R.string.thread_detail_ntap_label)
    + "</span>"
+ "</div>"
```

# XML style rules
----

## Use self closing tags

When an XML element doesn't have any content, you __must__ use self closing tags.

This is good:

```xml
<TextView
    android:id="@+id/text_view_profile"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```
        
This is __bad__ :

```xml
<!-- Don't do this! -->
<TextView
    android:id="@+id/text_view_profile"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" >
</TextView>
```


## Resources naming 

Resource IDs and names are written in __lowercase_underscore__

## ID naming

IDs should be prefixed with the name of the element in lowercase underscore. Prefix length must be 2-3 characters. For example:


| Element            | Prefix            |
| -----------------  | ----------------- |
| `TextView`         | `txt_`            |
| `ImageView`        | `img_`            | 
| `Button`           | `btn_`            |   
| `Menu`             | `mn_`             |
| `EditText`         | `et_`             |

Image view example:

```xml
<ImageView
    android:id="@+id/img_profile"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

Menu example:

```xml
<menu>
    <item
        android:id="@+id/menu_done"
        android:title="Done" />
</menu>
```

## Attributes ordering 

As a general rule you should try to group similar attributes together. A good way of ordering the most common attributes is:

1. View Id
2. Style
3. Layout width and layout height
4. Other layout attributes, sorted alphabetically
5. Remaining attributes, sorted alphabetically


## Organizing layout XMLs

- One attribute per line, indented by 4 spaces
- `android:id` as the first attribute always
- `android:layout_****` attributes at the top
- Tag closer `/>` on its own line, to facilitate ordering and adding attributes.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    >

    <TextView
        android:id="@+id/name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentRight="true"
        android:text="@string/name"
        style="@style/FancyText"
        />

    <include layout="@layout/reusable_part" />

</LinearLayout>
```

As a rule of thumb, attributes `android:layout_****` should be defined in the layout XML, while other attributes `android:****` should stay in a style XML. This rule has exceptions, but in general works fine. The idea is to keep only layout (positioning, margin, sizing) and content attributes in the layout files, while keeping all appearance details (colors, padding, font) in styles files.

The exceptions are:

- `android:id` should obviously be in the layout files
- `android:orientation` for a `LinearLayout` normally makes more sense in layout files
- `android:text` should be in layout files because it defines content
- Sometimes it will make sense to make a generic style defining `android:layout_width` and `android:layout_height` but by default these should appear in the layout files

## Use styles

Almost every project needs to properly use styles, because it is very common to have a repeated appearance for a view. At least you should have a common style for most text content in the application, for example:

```xml
<style name="ContentText">
    <item name="android:textSize">@dimen/font_normal</item>
    <item name="android:textColor">@color/basic_black</item>
</style>
```

Applied to TextViews:

```xml
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/price"
    style="@style/ContentText"
    />
```

You probably will need to do the same for buttons, but don't stop there yet. Go beyond and move a group of related and repeated `android:****` attributes to a common style.

## Styles and Themes

Unless the rest of resources, style names are written in __UpperCamelCase__.

## Split styles

**Split a large style file into other files.** You don't need to have a single `styles.xml` file. Android SDK supports other files out of the box, there is nothing magical about the name `styles`, what matters are the XML tags `<style>` inside the file. Hence you can have files `styles.xml`, `styles_home.xml`, `styles_item_details.xml`, `styles_forms.xml`. Unlike resource directory names which carry some meaning for the build system, filenames in `res/values` can be arbitrary.

## Strings

Name your strings with keys that resemble namespaces, and don't be afraid of repeating a value for two or more keys. Languages are complex, so namespaces are necessary to bring context and break ambiguity.

**Bad**
```xml
<string name="network_error">Network error</string>
<string name="call_failed">Call failed</string>
<string name="map_failed">Map loading failed</string>
```

**Good**
```xml
<string name="error.message.network">Network error</string>
<string name="error.message.call">Call failed</string>
<string name="error.message.map">Map loading failed</string>
```

Don't write string values in all uppercase. Stick to normal text conventions (e.g., capitalize first character). If you need to display the string in all caps, then do that using for instance the attribute [`textAllCaps`](http://developer.android.com/reference/android/widget/TextView.html#attr_android:textAllCaps) on a TextView.

**Bad**
```xml
<string name="error.message.call">CALL FAILED</string>
```

**Good**
```xml
<string name="error.message.call">Call failed</string>
```

## Colors

**`colors.xml` is a color palette.** There should be nothing else in your `colors.xml` than just a mapping from a color name to an RGBA value. Do not use it to define RGBA values for different types of buttons.

*Don't do this:*

```xml
<resources>
    <color name="button_foreground">#FFFFFF</color>
    <color name="button_background">#2A91BD</color>
    <color name="comment_background_inactive">#5F5F5F</color>
    <color name="comment_background_active">#939393</color>
    <color name="comment_foreground">#FFFFFF</color>
    <color name="comment_foreground_important">#FF9D2F</color>
    ...
    <color name="comment_shadow">#323232</color>
```

You can easily start repeating RGBA values in this format, and that makes it complicated to change a basic color if needed. Also, those definitions are related to some context, like "button" or "comment", and should live in a button style, not in `colors.xml`.

Instead, do this:

```xml
<resources>

    <!-- grayscale -->
    <color name="white"     >#FFFFFF</color>
    <color name="gray_light">#DBDBDB</color>
    <color name="gray"      >#939393</color>
    <color name="gray_dark" >#5F5F5F</color>
    <color name="black"     >#323232</color>

    <!-- basic colors -->
    <color name="green">#27D34D</color>
    <color name="blue">#2A91BD</color>
    <color name="orange">#FF9D2F</color>
    <color name="red">#FF432F</color>

</resources>
```

Ask for this palette from the designer of the application. The names do not need to be color names as "green", "blue", etc. Names such as "brand_primary", "brand_secondary", "brand_negative" are totally acceptable as well. Formatting colors as such will make it easy to change or refactor colors, and also will make it explicit how many different colors are being used. Normally for a aesthetic UI, it is important to reduce the variety of colors being used.

## Dimens

**Treat dimens.xml like colors.xml.** You should also define a "palette" of typical spacing and font sizes, for basically the same purposes as for colors. A good example of a dimens file:

```xml
<resources>

    <!-- font sizes -->
    <dimen name="font_larger">22sp</dimen>
    <dimen name="font_large">18sp</dimen>
    <dimen name="font_normal">15sp</dimen>
    <dimen name="font_small">12sp</dimen>

    <!-- typical spacing between two views -->
    <dimen name="spacing_huge">40dp</dimen>
    <dimen name="spacing_large">24dp</dimen>
    <dimen name="spacing_normal">14dp</dimen>
    <dimen name="spacing_small">10dp</dimen>
    <dimen name="spacing_tiny">4dp</dimen>

    <!-- typical sizes of views -->
    <dimen name="button_height_tall">60dp</dimen>
    <dimen name="button_height_normal">40dp</dimen>
    <dimen name="button_height_short">32dp</dimen>

</resources>
```

You should use the `spacing_****` dimensions for layouting, in margins and paddings, instead of hard-coded values, much like strings are normally treated. This will give a consistent look-and-feel, while making it easier to organize and change styles and layouts.

## View hierarchy

**Avoid a deep hierarchy of views.** Sometimes you might be tempted to just add yet another LinearLayout, to be able to accomplish an arrangement of views. This kind of situation may occur:

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    >

    <RelativeLayout
        ...
        >

        <LinearLayout
            ...
            >

            <LinearLayout
                ...
                >

                <LinearLayout
                    ...
                    >
                </LinearLayout>

            </LinearLayout>

        </LinearLayout>

    </RelativeLayout>

</LinearLayout>
```

Even if you don't witness this explicitly in a layout file, it might end up happening if you are inflating (in Java) views into other views.

A couple of problems may occur. You might experience performance problems, because there are is a complex UI tree that the processor needs to handle. Another more serious issue is a possibility of [StackOverflowError](http://stackoverflow.com/questions/2762924/java-lang-stackoverflow-error-suspected-too-many-views).

Therefore, try to keep your views hierarchy as flat as possible: learn how to use [RelativeLayout](https://developer.android.com/guide/topics/ui/layout/relative.html), how to [optimize your layouts](http://developer.android.com/training/improving-layouts/optimizing-layout.html) and to use the [`<merge>` tag](http://stackoverflow.com/questions/8834898/what-is-the-purpose-of-androids-merge-tag-in-xml-layouts).

## WebViews

**Beware of problems related to WebViews.** When you must display a web page, for instance for a news article, avoid doing client-side processing to clean the HTML, rather ask for a "*pure*" HTML from the backend programmers. [WebViews can also leak memory](http://stackoverflow.com/questions/3130654/memory-leak-in-webview) when they keep a reference to their Activity, instead of being bound to the ApplicationContext. Avoid using a WebView for simple texts or buttons, prefer TextViews or Buttons.

## Unit tests 

The test classes should match the name of the class that the tests are targeting followed by `Test`. For example, If we create a test class that contains test for the `DataManager`, we should name it `DataManagerTest`.

The name of the tests must start with `should` followed by the expected behaviour. For example:

* `shouldLoadUserData()`
* `shouldThrowExceptionWhenLoadingUser()`

# Code Style 
---

Download and import [Square's Java and Android Codestyle](https://github.com/square/java-code-styles)

Additional : 

* Open setting
* Go to Editor > Code Style 
* Scheme > select to SquareAndroid
* Go to Java > Code Generation
* add "m" prefix on Field
* add "s" prefix on Static Field

Make sure you __check__ the reformat and rearrange code option on commit message dialog.

# File Template
---

## Copyright / Author

Add copyright / author for every file you made, using this convention :

```java
/* Copyright (C) "year" Elken Sdn Bhd - All Rights Reserved
 *
 * Created by "your name" on "date".
 * "your company email" / "your personal email"
 */
```

you can copy this template to Android Studio copyright profile 

```java
Copyright (c) $today.year. Elken Sdn Bhd - All Rights Reserved

Created by <your_name> on $today.format("dd/MM/yyyy")
<your_company_email> / <your_personal_email>
```

result : 

```java
/* Copyright (C) 2017 Elken Sdn Bhd - All Rights Reserved
 *
 * Created by Oky Nugroho K on 26/12/17.
 * on.kusumo@elken.com / okynk91@gmail.com
 */
```

set the Android Studio's Copyright formatting to :

* __No copyright__ for Groovy
* __Use default settings and Before Doctype__ for HTML
* __Use default settings and Before Class__ for Java
* __No copyright__ for Properties
* __No copyright__ for SPI
* __Use default settings and Before Doctype__ for XML

Put the copyright right before the class declaration, example : 

```
package com.elken.nibs.features.screen;

import android.support.v7.widget.RecyclerView;
.......
import java.util.List;

/* Copyright (C) 2017 Elken Sdn Bhd - All Rights Reserved
 *
 * Created by Oky Nugroho K on 26/12/17.
 * on.kusumo@elken.com / okynk91@gmail.com
 */

public class ActivitySomething extends ToolbarActivity {
    .....
}
```

# References
---
* [Android Best Practices](https://github.com/futurice/android-best-practices)
* [Managing Your App's Memory](https://developer.android.com/training/articles/memory.html)
* [Android Clean Architecture](https://github.com/android10/Android-CleanArchitecture)
* [ribot/android-boilerplate](https://github.com/ribot/android-boilerplate)
* [ribot/android-guidelines](https://github.com/ribot/android-guidelines/blob/master/project_and_code_guidelines.md)
* [AOSP Java Code Style](https://source.android.com/setup/code-style)
