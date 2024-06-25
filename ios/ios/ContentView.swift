import common
import SwiftUI

struct ContentView: View {
    var body: some View {
        GeometryReader { geometry in
            SpotifyUIViewController()
                .preferredColorScheme(.dark)
                .ignoresSafeArea(.all)
        }
    }
}


struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .previewLayout(.sizeThatFits)
    }
}
