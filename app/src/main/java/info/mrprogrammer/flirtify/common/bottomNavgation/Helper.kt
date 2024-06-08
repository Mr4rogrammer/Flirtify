package info.mrprogrammer.flirtify.common.bottomNavgation

import info.mrprogrammer.flirtify.R
import info.mrprogrammer.ui_manager.ui.compose.navigation.model.BottomNavigationBarDataModel


fun getBottomNavigationDetails(): List<BottomNavigationBarDataModel> {
    return listOf(
        BottomNavigationBarDataModel(
            id = 0,
            activeIcon = R.drawable.home_active,
            inActiveIcon = R.drawable.home,
        ),
        BottomNavigationBarDataModel(
            id = 1,
            activeIcon = R.drawable.discover_active,
            inActiveIcon = R.drawable.discover,
        ),
        BottomNavigationBarDataModel(
            id = 2,
            activeIcon = R.drawable.add,
            inActiveIcon = R.drawable.add,
        ),
        BottomNavigationBarDataModel(
            id = 3,
            activeIcon = R.drawable.mathes_active,
            inActiveIcon = R.drawable.matches,
        ),
        BottomNavigationBarDataModel(
            id = 4,
            activeIcon = R.drawable.message_active,
            inActiveIcon = R.drawable.message,
        )
    )
}
