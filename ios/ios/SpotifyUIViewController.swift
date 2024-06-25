import common
import Foundation
import SwiftUI

struct SpotifyUIViewController: UIViewControllerRepresentable {

    func updateUIViewController(_ uiViewController: UIViewControllerType,context: Context) {}
    
    func makeUIViewController(context: Context) -> some UIViewController {
        let factory = SpotifyUIViewControllerFactory()
        return factory.create()
    }
}
