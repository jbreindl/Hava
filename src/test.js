describe("basic testing", function () {
    let d = distance(2,0,2,0);

    //both shark and minnow moved in a circle because I couldn't figure out multiple unit tests
    it("should be 2*sqrt(2)", function () {
        expect(d).toBe(2*Math.sqrt(2))
    });
});