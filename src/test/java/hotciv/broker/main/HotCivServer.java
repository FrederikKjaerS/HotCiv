package hotciv.broker.main;

import frds.broker.Invoker;
import frds.broker.ipc.http.UriTunnelServerRequestHandler;
import frds.broker.ipc.socket.SocketServerRequestHandler;
import hotciv.broker.GameInvoker;
import hotciv.broker.HotCivRootInvoker;
import hotciv.factories.SemiCivFactory;
import hotciv.factories.SemiCivGUIFactory;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.service.GameNameService;
import hotciv.standard.GameImpl;
import hotciv.stub.StubGameBroker;
import hotciv.view.tool.CompositionTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class HotCivServer {

    public static void main(String[] args) throws Exception {
        new HotCivServer(); // No error handling!
    }


    public HotCivServer() throws Exception {
        int port = 37123;

        Game gameServant = new GameImpl(new SemiCivFactory());
        Invoker invoker = new HotCivRootInvoker(gameServant);

        // Configure a http based server request handler
        UriTunnelServerRequestHandler urtsrh =
                new UriTunnelServerRequestHandler();
        urtsrh.setPortAndInvoker(port, invoker);
        gameServant.changeProductionInCityAt(new Position(8,12), GameConstants.ARCHER);

        System.out.println("=== HotCiv Socket based Server Request Handler (port:"
                + port + ") ===");
        System.out.println(" Use ctrl-c to terminate!");
        urtsrh.start();
    }
}

