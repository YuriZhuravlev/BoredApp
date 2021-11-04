package com.example.boredapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testNote() {
        createNote();
        editNote();
        deleteNote();
    }

    private void createNote() {
        final String textTitle = "Test Note";
        final String textNote = "Lorem Ipsum";
        onView(withId(R.id.notes_menu_item_add)).perform(ViewActions.click());
        onView(withId(R.id.note_title)).perform(ViewActions.typeText(textTitle));
        onView(withId(R.id.note_edit_text)).perform(ViewActions.typeText(textNote));
        onView(isRoot()).perform(ViewActions.closeSoftKeyboard());
        pressBack();
        onView(withId(R.id.notes_recycler_view))
                .check(matches(
                        atPositionOnView(
                                0,
                                withText(textTitle),
                                R.id.item_note_title
                        )
                ));
        onView(withId(R.id.notes_recycler_view))
                .check(matches(
                        atPositionOnView(
                                0,
                                withText(textNote),
                                R.id.item_note_info
                        )
                ));
    }

    private void editNote() {
        final String textTitle = "New title for note";
        onView(withId(R.id.notes_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        onView(withId(R.id.note_title)).perform(ViewActions.clearText());
        onView(withId(R.id.note_title)).perform(ViewActions.typeText(textTitle));
        onView(isRoot()).perform(ViewActions.closeSoftKeyboard());
        pressBack();
        onView(withId(R.id.notes_recycler_view))
                .check(matches(
                        atPositionOnView(
                                0,
                                withText(textTitle),
                                R.id.item_note_title
                        )
                ));
    }

    private void deleteNote() {
        onView(withId(R.id.notes_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.longClick()));
        onView(withId(R.id.notes_menu_item_delete)).perform(ViewActions.click());
    }

    private static void pressBack() {
        onView(isRoot()).perform(ViewActions.pressBack());
    }

    private static Matcher<View> atPositionOnView(final int position, final Matcher<View> itemMatcher,
                                                  @NonNull final int targetViewId) {

        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has view id " + itemMatcher + " at position " + position);
            }

            @Override
            public boolean matchesSafely(final RecyclerView recyclerView) {
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                View targetView = viewHolder.itemView.findViewById(targetViewId);
                return itemMatcher.matches(targetView);
            }
        };
    }
}