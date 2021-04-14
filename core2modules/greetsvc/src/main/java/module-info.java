module learn.core.greetsvc {
    exports learn.core2.ch09modules.greetsvc;
    provides learn.core2.ch09modules.greetsvc.GreeterService with
            learn.core2.ch09modules.greetsvc.internal.FrenchGreeterService,
            learn.core2.ch09modules.greetsvc.internal.GermanGreeterService;
}
