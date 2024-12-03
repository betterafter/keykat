import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebView
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.PhoneAndroid
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.android.material.internal.ContextUtils.getActivity
import com.keykat.domain.profile.entity.ProfileEntity
import com.keykat.presentation.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ProfileTopWidget(
    navController: NavController,
    profileEntity: ProfileEntity,
    scrollState: ScrollState
) {

    val namePosition by animateIntOffsetAsState(
        targetValue = IntOffset(scrollState.value / 3, 0),
        label = ""
    )
    val contactPosition by animateIntOffsetAsState(
        targetValue = IntOffset(scrollState.value / 2, 0),
        label = ""
    )

    var currentSnsLink by remember { mutableStateOf("") }
    val currentContext = LocalContext.current

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var dismissOnBackPress by remember { mutableStateOf(false) }

    val requester = remember { FocusRequester() }
    val backPressedDispatcherOwner = LocalOnBackPressedDispatcherOwner.current

    var webView: WebView? = null

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = profileEntity.profileUrl,
            alignment = Alignment.Center,
            contentDescription = "profile",
            modifier = Modifier
                .padding(top = 10.dp, bottom = 20.dp)
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
            Row {
                Icon(
                    imageVector = Icons.Rounded.Email,
                    contentDescription = "이메일 아이콘",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(2.dp)
                )
                Text(
                    text = profileEntity.email.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Row {
                Icon(
                    imageVector = Icons.Rounded.PhoneAndroid,
                    contentDescription = "이메일 아이콘",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(2.dp)

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
                        AsyncImage(
                            modifier = Modifier
                                .padding(start = 5.dp, top = 10.dp, end = 5.dp, bottom = 10.dp)
                                .size(25.dp)
                                .clickable {

                                    showBottomSheet = true
                                    currentSnsLink = curr.webUrl.toString()
                                    navController.navigate(
                                        route = Screen.Web.createRoute(currentSnsLink),
                                    )
//                                    currentSnsLink = curr.url.toString()
//                                    currentSnsLink = "gh://keykat7"
//                                    println("[keykat] $currentSnsLink")
//                                    try {
//                                        val intent = Intent(Intent.ACTION_VIEW).apply {
//                                            data = Uri.parse(currentSnsLink)
//                                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                                        }
//                                        currentContext.startActivity(intent)
//                                    } catch (e: ActivityNotFoundException) {
//                                        showBottomSheet = true
//                                        currentSnsLink = curr.webUrl.toString()
//                                    }
                                },
                            model = curr.icon,
                            contentDescription = curr.name,
                        )
                    }
                }
            }
//            if (showBottomSheet) {
//                ModalBottomSheet(
//                    onDismissRequest = {
//                        webView?.destroy()
//                        showBottomSheet = false
//                    },
//                    sheetState = sheetState,
//                    dragHandle = { BottomSheetDefaults.DragHandle() },
//                    modifier = Modifier.focusRequester(requester)
//                        .focusable()
//                        .onPreviewKeyEvent {
//                            println("[keykat] onPreviewKeyEvent")
//                            if (it.key == Key.Back && it.type == KeyEventType.KeyUp && !it.nativeKeyEvent.isCanceled) {
//                                backPressedDispatcherOwner?.onBackPressedDispatcher?.onBackPressed()
//                                return@onPreviewKeyEvent true
//                            }
//                            return@onPreviewKeyEvent false
//                        },
//                    properties = ModalBottomSheetProperties(
//                        isFocusable = true,
//                        securePolicy = SecureFlagPolicy.SecureOff,
//                        shouldDismissOnBackPress = false
//                    )
//                ) {
//                    AndroidWebView(
//                        url = currentSnsLink,
//                        update = {
//                            webView = it
//                        }
//                    )
//                }
//            }
        }
    }

//    ModalBottomSheet(onDismissRequest = { /*TODO*/ }) {
//        Box {
//            BackHandler {
//                println("[keykat] dsf")
//            }
//        }
//    }
}