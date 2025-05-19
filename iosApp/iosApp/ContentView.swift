import SwiftUI

import SwiftUI
import UIKit
import shared

struct ComposeContentView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        ComposeUIViewController {
            AppNavGraphKt.AppNavGraph()
        }
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {

    }
}
