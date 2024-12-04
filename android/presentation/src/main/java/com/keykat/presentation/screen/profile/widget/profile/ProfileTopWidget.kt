import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.keykat.domain.profile.entity.ProfileEntity
import com.keykat.presentation.navigation.Screen
import com.keykat.presentation.profileViewModel
import com.keykat.presentation.screen.profile.ProfileViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileTopWidget(
    navController: NavController,
    profileEntity: ProfileEntity,
    currentIndex: Int,
    viewModel: ProfileViewModel = profileViewModel()
) {
    var namePosX by remember { mutableIntStateOf(800) }
    var contactPosX by remember { mutableIntStateOf(1800) }
    val scrollState = viewModel.getScrollState()

    val namePosition by animateIntOffsetAsState(
        targetValue = IntOffset(namePosX, 0),
        label = ""
    )
    val contactPosition by animateIntOffsetAsState(
        targetValue = IntOffset(contactPosX, 0),
        label = ""
    )

    LaunchedEffect(scrollState?.currentPageOffsetFraction) {
        snapshotFlow { scrollState?.currentPage }.collect { page ->
            if (page == currentIndex) {
                if (scrollState != null) {
                    val position = scrollState.getOffsetFractionForPage(viewModel.currentPage.value)
                    namePosX = (position * 100 * 5).toInt()
                    contactPosX = (position * 100 * 8).toInt()
                }
            } else {
                namePosX = 800
                contactPosX = 1800
            }
        }
    }

    var currentSnsLink by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = profileEntity.profileUrl,
            alignment = Alignment.Center,
            contentDescription = "profile",
            modifier = Modifier
                .padding(top = 40.dp, bottom = 20.dp)
                .width(120.dp)
                .height(120.dp)
                .clip(CircleShape)
        )
        Text(
            text = profileEntity.name.toString(),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .offset { namePosition }
        )
        Box(modifier = Modifier.height(12.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .offset { contactPosition }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Email,
                    contentDescription = "이메일 아이콘",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(2.dp)
                )

                Box(
                    modifier = Modifier
                        .width(14.dp)
                        .height(16.dp)
                        .padding(start = 4.dp, end = 8.dp)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                )

                Text(
                    text = profileEntity.email.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Phone,
                    contentDescription = "이메일 아이콘",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(2.dp)
                )

                Box(
                    modifier = Modifier
                        .width(12.dp)
                        .height(16.dp)
                        .padding(start = 2.dp, end = 8.dp)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.primary
                        )
                )

                Text(
                    text = profileEntity.tel.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier

            ) {
                profileEntity.sns?.map { curr ->
                    curr?.icon?.let {
                        Box(
                            modifier = Modifier
                                .padding(3.dp)
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .border(
                                        border = BorderStroke(
                                            1.dp,
                                            color = MaterialTheme.colorScheme.primary
                                        ),
                                        shape = RoundedCornerShape(30)
                                    )
                                    .padding(4.dp)
                                    .size(25.dp)
                                    .clickable {
                                        showBottomSheet = true
                                        currentSnsLink = curr.url ?: curr.webUrl.toString()
                                        navController.navigate(
                                            route = Screen.Web.createRoute(currentSnsLink),
                                        )
                                    },
                                model = curr.icon,
                                contentDescription = curr.name,
                            )
                        }
                    }
                }
            }
        }
    }
}