package com.lynx.testtask65apps.other;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.lynx.testtask65apps.R;

import java.util.LinkedList;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;

public class CustomNavigator implements Navigator {

    private final Activity activity;
    private final FragmentManager fragmentManager;
    private final int containerId;
    private LinkedList<String> stackCopy;


    public CustomNavigator(Activity activity, FragmentManager fragmentManager, int containerId) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
    }

    @Override
    public void applyCommand(Command command) {

        copyStackToLocal();

        if (command instanceof Forward) {
            forwardCommand((Forward) command);
        } else if (command instanceof Replace) {
            replaceCommand((Replace) command);
        } else if (command instanceof BackTo) {
            backTo((BackTo) command);
        } else if (command instanceof Back) {
            fragmentBack();
        }
    }

    private void backTo(BackTo command) {
        String key = command.getScreenKey();

        if (key == null) {
            backToRoot();
        } else {
            int index = stackCopy.indexOf(key);
            int size = stackCopy.size();

            if (index != -1) {
                for (int i = 1; i < size - index; i++) {
                    stackCopy.removeLast();
                }
                fragmentManager.popBackStack(key, 0);
            }
        }
    }

    private void backToRoot() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        stackCopy.clear();
    }

    private void replaceCommand(Replace command) {

        Fragment fragment = createFragment(command.getScreenKey(), command.getTransitionData());

        if (stackCopy.size() > 0) {
            fragmentManager.popBackStack();
            stackCopy.removeLast();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction
                    .setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_top, R.anim.enter_from_top, R.anim.exit_to_top)
                    .replace(containerId, fragment)
                    .addToBackStack(command.getScreenKey())
                    .commit();
            stackCopy.add(command.getScreenKey());

        } else {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction
                    .replace(containerId, fragment)
                    .commit();
        }
    }

    private void fragmentBack() {
        if (stackCopy.size() > 0) {
            fragmentManager.popBackStack();
            stackCopy.removeLast();
        }
    }

    private void forwardCommand(Forward forward) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_top, R.anim.exit_to_top, R.anim.enter_from_top, R.anim.exit_to_top)
                .replace(containerId, createFragment(forward.getScreenKey(), forward.getTransitionData()))
                .addToBackStack(forward.getScreenKey())
                .commit();
        stackCopy.add(forward.getScreenKey());
    }

    private void copyStackToLocal() {
        stackCopy = new LinkedList<>();

        final int stackSize = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < stackSize; i++) {
            stackCopy.add(fragmentManager.getBackStackEntryAt(i).getName());
        }
    }

    private Fragment createFragment(String screenKey, Object data) {
        return Screen.valueOf(screenKey).create((Bundle) data);
    }

}
